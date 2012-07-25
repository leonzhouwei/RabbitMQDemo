import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class AGearSender {
	public static void main(String[] argv) throws Exception {
		
//		if (argv == null || argv.length < 1) {
//			System.err.println("argv cannot be null or empty(AGearSender.java:9)");
//			System.exit(1);
//		}

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(AGearConfig.RABBITMQ_SERVER_IP);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(AGearConfig.RABBITMQ_EXCHANGE_NAME, "direct");

//		String bindKey = getBindKey(argv);
		String bindKey = AGearConfig.RABBITMQ_EXCHANGE_QUEUE_BIND_KEY_IOS;
//		String message = getMessage(argv, AGearConfig.QUEUE_MSG_DELIMITER);
		String message = "AGearSender";
		
		channel.basicPublish(AGearConfig.RABBITMQ_EXCHANGE_NAME, bindKey, null,
				message.getBytes());
		System.out.println(" [x] Sent '" + bindKey + "':'" + message + "'");

		channel.close();
		connection.close();
	}

	private static String getBindKey(String[] strings) {
		return strings[0];
	}

	private static String getMessage(String[] strings, String delimiter) {
		int length = strings.length;
		StringBuilder words = new StringBuilder(strings[1]);
		for (int i = 2; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}

}
