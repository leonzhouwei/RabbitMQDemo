import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;


public class AGearIosBuilder2 {

	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(AGearConfig.QUEUE_SERVER_IP);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(AGearConfig.EXCHANGE_NAME, "direct");
		String bindKey = AGearConfig.EXCHANGE_QUEUE_BIND_KEY_IOS;
		channel.queueDeclare(AGearConfig.QUEUE_NAME_IOS, false, false, false, null);
		channel.queueBind(AGearConfig.QUEUE_NAME_IOS, AGearConfig.EXCHANGE_NAME, bindKey);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(AGearConfig.QUEUE_NAME_IOS, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String routingKey = delivery.getEnvelope().getRoutingKey();

			System.out.println(" [x] Received '" + routingKey + "':'" + message
					+ "'");
		}
	}


}
