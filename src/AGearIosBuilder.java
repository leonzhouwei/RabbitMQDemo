import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class AGearIosBuilder {
	public static void main(String[] argv) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(AGearConfig.RABBITMQ_SERVER_IP);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(AGearConfig.RABBITMQ_EXCHANGE_NAME, "direct");
		String bindKey = AGearConfig.RABBITMQ_EXCHANGE_QUEUE_BIND_KEY_IOS;
		channel.queueDeclare(AGearConfig.RABBITMQ_QUEUE_NAME_IOS, false, false, false, null);
		channel.queueBind(AGearConfig.RABBITMQ_QUEUE_NAME_IOS, AGearConfig.RABBITMQ_EXCHANGE_NAME, bindKey);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(AGearConfig.RABBITMQ_QUEUE_NAME_IOS, true, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			String routingKey = delivery.getEnvelope().getRoutingKey();

			System.out.println(" [x] Received '" + routingKey + "':'" + message
					+ "'");
		}
	}

}
