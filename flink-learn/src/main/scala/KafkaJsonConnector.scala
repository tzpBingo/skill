
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.{TableEnvironment, Types}
import org.apache.flink.table.descriptors.{Json, Kafka, Rowtime, Schema}
import org.apache.flink.types.Row
import org.apache.flink.streaming.api.scala._

object KafkaJsonConnector {

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    // create a TableEnvironment for streaming queries
    val tableEnv = TableEnvironment.getTableEnvironment(env)

    tableEnv
      .connect(
        new Kafka()
          .version("universal")
          .topic("i4-flink-topic")
          .startFromEarliest()
          .property("zookeeper.connect", "i4server:2181")
          .property("bootstrap.servers", "i4server:9092"))
      .withFormat(
        new Json().deriveSchema()
      )
      .withSchema(
        new Schema()
          .field("id","INT")
          .field("age","INT")
          .field("name","VARCHAR")
          .field("password","VARCHAR")
      )
      .inAppendMode()
      .registerTableSource("stu")

    val stream = tableEnv.scan("stu")
    stream.printSchema()
//    tableEnv.toAppendStream[Row](stream).print().setParallelism(1)
    env.execute("sql")
  }

}

