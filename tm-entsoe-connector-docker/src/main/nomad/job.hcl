job "{{DOCKER_IMAGE_NAME}}" {
  region = "global"
  datacenters = [
    "dc1"]
  type = "service"

  update {
    stagger = "30s"
    max_parallel = 1
  }
  constraint {
    attribute = "${meta.node_type}"
    operator = "!="
    value = "storage"
  }
  constraint {
    attribute = "${meta.node_type}"
    operator = "="
    value = "primary"
  }
  constraint {
    attribute = "${attr.kernel.name}"
    value = "linux"
  }

  group "tasks" {

    task "{{DOCKER_IMAGE_NAME}}" {
      constraint {
        attribute = "${attr.kernel.name}"
        value = "linux"
      }

      driver = "docker"

      config {
        dns_servers = [
          "${NOMAD_IP_http}"]
        load = "{{DOCKER_IMAGE_NAME}}.tar"
        image = "docker.trendminer.net:5000/{{DOCKER_IMAGE_NAME}}:{{DOCKER_IMAGE_TAG}}"
        force_pull = true
        port_map {
          http = 10029
          metrics = 20029
          {{#if_eq NOMAD_TEMPLATE_ENV 'dev'}}
          debug = 30029
          {{/if_eq}}
          grpc = 50051
        }
        logging {
          type = "syslog"
          config {
            syslog-facility = "local1"
            tag = "NO-VPN-CLOUD-CONNECTOR"
          }
        }
      }

      artifact {
        source = "http://primary.node.consul:8888/{{DOCKER_IMAGE_NAME}}.tar"
      }

      env {
        MAX_HEAP_SIZE = "512M"
        OCS_DB_URL = "jdbc:postgresql://primary.node.consul:5432/tm_ocs"
      }

      service {
        name = "{{DOCKER_IMAGE_NAME}}"
        tags = [
          "PROXY_PATH=/no-vpn-cloud"]
        port = "http"

        check {
          type = "http"
          path = "/api/actuator/health"
          interval = "20s"
          timeout = "2s"

          check_restart {
            limit = 3
            grace = "120s"
            ignore_warnings = false
          }
        }
      }

      resources {
        cpu = 50
        memory = 1024

        network {
          mbits = 1
          port "http" {
            static = "10029"
          }
          port "metrics" {
            static = "20029"
          }
          {{#if_eq NOMAD_TEMPLATE_ENV 'dev'}}
          port "debug" { static = "30029" }
          {{/if_eq}}
	  port "grpc" {
            static = "50051"
	  }
        }
      }
    }
  }
}
