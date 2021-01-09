until curl -I -s http://couchbase:8091/ui/index.html; do
  echo 'Waiting for Couchbase to initialise'
  sleep 1
done

dockerProcesses=`docker ps`
containerName=$(echo "$dockerProcesses" | grep "couchbase" | awk '{print $1}')

docker exec "$containerName" couchbase-cli cluster-init --cluster "couchbase://127.0.0.1" --cluster-name "mylistcluster" \
  --cluster-username "admin" --cluster-password "password" \
  --services "data,index,query" --cluster-ramsize 500 \
  --cluster-index-ramsize 256 --index-storage-setting "memopt"
