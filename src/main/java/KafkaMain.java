import spouts.WordReader;
import storm.kafka.KafkaSpout;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import bolts.LogPrinter;

public class KafkaMain {
    public static void main(String[] args) throws InterruptedException {
        //Topology definition
        TopologyBuilder builder = new TopologyBuilder();
        //builder.setSpout("kafka-spout",new WordReader());
        builder.setSpout("kafka-spout",new KafkaSpout());
        builder.setBolt("log-printer", new LogPrinter())
                .shuffleGrouping("kafka-spout");

        //Configuration
        Config conf = new Config();
        conf.put("wordsFile", args[0]);
        conf.setDebug(false);

        //Topology run
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Toplogie", conf,
                               builder.createTopology());
        Thread.sleep(1000);
        cluster.shutdown();
    }
}