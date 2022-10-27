package cn.koala.bucket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BucketApiTest {
  private final ObjectMapper mapper = new ObjectMapper();
  @Autowired
  private MockMvc mockMvc;

  @Test
  @Order(1)
  public void cru() throws Exception {
    DataSourceEntity dataSource = DataSourceEntity.builder()
      .id("999")
      .code("test")
      .name("测试数据源")
      .url("jdbc:mysql://bj-cdb-9amt73r4.sql.tencentcdb.com:59997/bucket")
      .username("test")
      .password("test@5015021301")
      .build();
    mockMvc.perform(post("/api/data-sources").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dataSource)))
      .andExpect(status().isOk());
    dataSource.setCode("test2");
    mockMvc.perform(put("/api/data-sources/999").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(dataSource)))
      .andExpect(status().isOk());
    mockMvc.perform(get("/api/data-sources"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    mockMvc.perform(get("/api/data-sources/999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.code", equalTo("test2")));
    ReportEntity report = ReportEntity.builder()
      .id("999")
      .code("test")
      .name("测试报告")
      .dataSource(dataSource)
      .selectSql("select * from t_report where id = '$id'")
      .config("测试配置")
      .build();
    mockMvc.perform(post("/api/reports").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(report)))
      .andExpect(status().isOk());
    report.setCode("test2");
    mockMvc.perform(put("/api/reports/999").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(report)))
      .andExpect(status().isOk());
    mockMvc.perform(get("/api/reports"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.content", hasSize(1)));
    mockMvc.perform(get("/api/reports/999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data.code", equalTo("test2")));
  }

  @Test
  @Order(2)
  public void data() throws Exception {
    mockMvc.perform(get("/api/reports/999/data?$id=999"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.data", hasSize(1)));
  }

  @Test
  @Order(3)
  public void d() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/data-sources/999"))
      .andExpect(status().isOk());
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/reports/999"))
      .andExpect(status().isOk());
  }
}
