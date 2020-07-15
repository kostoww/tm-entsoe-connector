FROM docker.trendminer.net:5000/java-base:latest
ADD @embed.destination.file@ /@embed.destination.file@
ENV MAX_HEAP_SIZE="512M"
ENV METRICS_PORT 20029
ENV DEBUG_PORT 30029
EXPOSE 10029 20029 30029
VOLUME /config
ENV JAR_FILE /@embed.destination.file@
