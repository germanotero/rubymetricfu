package hudson.plugins.rubyMetrics;

import hudson.plugins.rubyMetrics.model.MetricFuResults;

import org.yaml.snakeyaml.*;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;

import java.util.Map;

public class MetricFuParser {

  private File yamlReport;

  public MetricFuParser(File yamlReport) {
    this.yamlReport = yamlReport;
  }

  public MetricFuResults parse() {
    MetricFuResults result = new MetricFuResults();

    try {
      InputStream input = new FileInputStream(yamlReport);
      Yaml yaml = new Yaml();
      Map data = (Map) yaml.load(input);

      Map flogData = (Map) data.get(":flog");
      Map flayData = (Map) data.get(":flay");

      result.setFlogMethodAverage((String) (flogData.get(":average").toString()));
      result.setFlogTotal((String) (flogData.get(":total").toString()));

      result.setFlayTotal((String) flayData.get(":total_score").toString());
    } catch (Exception e) {
      System.out.println(e);
    }
    return result;
  }
}
