docker run -d -p8091:8091 -p11210:11210 --name=couchbasedb couchbase:6.0.2

until curl -I -s http://localhost:8091/ui/index.html; do
  echo 'Waiting for Couchbase to initialise'
  sleep 1
done

docker exec couchbasedb couchbase-cli cluster-init --cluster "couchbase://127.0.0.1" --cluster-name "mylistcluster" \
  --cluster-username "admin" --cluster-password "password" \
  --services "data,index,query" --cluster-ramsize 500 \
  --cluster-index-ramsize 256 --index-storage-setting "memopt"
