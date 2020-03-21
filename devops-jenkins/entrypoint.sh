git config --global http.postBuffer 1000M
git config --global http.maxRequestBuffer 1000M
git config --global core.compression 0

filebeat -c /etc/filebeat/filebeat.yml &
exec bash -c /usr/local/bin/jenkins.sh