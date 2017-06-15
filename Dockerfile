FROM maven:3.3.3

USER root
RUN mkdir -p /mnt/nfs

USER jenkins
VOLUME /mnt/nfs
