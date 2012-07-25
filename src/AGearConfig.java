public class AGearConfig {
	  public static final String RABBITMQ_EXCHANGE_NAME = "direct_logs";
	  public static final String RABBITMQ_SERVER_IP = "192.168.56.101";
	  public static final String RABBITMQ_QUEUE_NAME_IOS = "iosq";
	  public static final String RABBITMQ_QUEUE_NAME_ANDROID = "androidq";
	  public static final String RABBITMQ_QUEUE_NAME_WP = "wpq";
	  public static final String RABBITMQ_EXCHANGE_QUEUE_BIND_KEY_IOS = "ios";
	  public static final String RABBITMQ_EXCHANGE_QUEUE_BIND_KEY_ANDROID = "android";
	  public static final String RABBITMQ_EXCHANGE_QUEUE_BIND_KEY_WP = "wp"; // windows phone
	  public static final String RABBITMQ_QUEUE_MSG_DELIMITER = ";";
}
