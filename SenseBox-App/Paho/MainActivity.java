public void conn(View v){

    try {
        IMqttToken token = client.connect();
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Toast.makeText(MainActivity.this, "Connected to test.mosquitto.org!", Toast.LENGTH_LONG).show();
                setSubscription();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Toast.makeText(MainActivity.this, "Connection failed!", Toast.LENGTH_LONG).show();

            }
        });
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

public void disconn(View v){

    try {
        IMqttToken token = client.disconnect();
        token.setActionCallback(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                Toast.makeText(MainActivity.this, "Disconnected from test.mosquitto.org!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                Toast.makeText(MainActivity.this, "Disconnection failed!", Toast.LENGTH_LONG).show();

            }
        });
    } catch (MqttException e) {
        e.printStackTrace();
    }
}

public class MainActivity extends AppCompatActivity {

    static String topicStr = "t0th/temperature";
    static String topicStr2 = "t0th/humidity";

    static String MQTTHOST = "tcp://test.mosquitto.org:1883";

    MqttAndroidClient client;

    TextView subText;
    TextView subText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subText = (TextView)findViewById(R.id.subText);
        subText2 = (TextView)findViewById(R.id.subText2);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(MainActivity.this, MQTTHOST, clientId);
