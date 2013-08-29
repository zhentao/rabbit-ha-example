This project is to illustrate how to use spring-amqp and rabbitmq's HA feature.
	
Configure 2-node rabbit cluster on a Mac machine:

1. download the latest version from http://www.rabbitmq.com/download.html
2. extract the tarball file to folder rabbit1
3. make a copy of rabbit1 to rabbit2
4. create file rabbitmq-env.conf under rabbit1/etc/rabbitmq/ with the following content:
	NODE_PORT=5672
	NODENAME=rabbit1@localhost
5. create file rabbitmq-env.conf unser rabbit2/etc/rabbitmq/ with the following content:
	NODE_PORT=5673
	NODENAME=rabbit2@localhost
6. under folder rabbit1, run the following command to start node rabbit1
	sbin/rabbitmq-server
7. under folder rabbit2, run the following commands to join cluster rabbit1:
	sbin/rabbitmq-server
	sbin/rabbitmqctl stop_app
	sbin/rabbitmqctl join_cluster rabbit1@localhost
	sbin/rabbitmqctl start_app
8. run the following command under either rabbit1 or rabbit2 to show the cluster status:
	sbin/rabbitmqctl cluster_status
	
Setup Highly Available Queues:

1. run the following command under either rabbit1 or rabbit2:
	sbin/rabbitmqctl set_policy ha-all "\." '{"ha-mode":"all"}'
	
This will configure all queues in this cluster are mirrored queues.  See rabbitmq document for details.

Now you have configured 2-node cluster with HA queues on a Mac machine.

start consumer:
	mvn compile exec:java -Dexec.mainClass="com.zhentao.amqp.example.consumer.ConsumerMain"
	
start producer:
	mvn compile exec:java -Dexec.mainClass="com.zhentao.amqp.example.producer.ProducerMain"
	
If you stop one of the node, you can see the program still runs well.