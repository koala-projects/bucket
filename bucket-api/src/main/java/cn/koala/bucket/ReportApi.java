package cn.koala.bucket;

import cn.koala.swagger.PageableAsQueryParam;
import cn.koala.web.DataResponse;
import cn.koala.web.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Houtaroy
 */
@RequestMapping("/api/reports")
@RestController
@SecurityRequirement(name = "spring-security")
@Tag(name = "报表管理")
public interface ReportApi {

  /**
   * 根据条件分页查询报表
   *
   * @param parameters 查询条件
   * @param pageable   分页条件
   * @return 报表列表
   */
  @Operation(summary = "根据条件分页查询报表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReportPageResult.class))}
  )
  @Parameter(in = ParameterIn.QUERY, name = "code", description = "报表代码", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "name", description = "报表名称", schema = @Schema(type = "string"))
  @Parameter(in = ParameterIn.QUERY, name = "dataSourceId", description = "数据源ID", schema = @Schema(type = "string"))
  @PageableAsQueryParam
  @GetMapping
  DataResponse<Page<Report>> page(@Parameter(hidden = true) @RequestParam Map<String, Object> parameters,
                                  @Parameter(hidden = true) Pageable pageable);

  /**
   * 根据id查询报表
   *
   * @param id 报表id
   * @return 报表
   */
  @Operation(summary = "根据id查询报表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReportResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "报表id", schema = @Schema(type = "string"))
  @GetMapping("{id}")
  DataResponse<Report> loadById(@PathVariable("id") String id);

  /**
   * 创建报表
   *
   * @param entity 报表数据实体
   * @return 报表
   */
  @Operation(summary = "创建报表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReportResult.class))}
  )
  @PostMapping
  DataResponse<Report> create(@RequestBody ReportEntity entity);

  /**
   * 更新报表
   *
   * @param id     报表id
   * @param entity 报表数据实体
   * @return 操作结果
   */
  @Operation(summary = "更新报表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "报表id", schema = @Schema(type = "string"))
  @PutMapping("{id}")
  Response update(@PathVariable("id") String id, @RequestBody ReportEntity entity);

  /**
   * 删除报表
   *
   * @param id 报表id
   * @return 操作结果
   */
  @Operation(summary = "删除报表")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Response.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "报表id", schema = @Schema(type = "string"))
  @DeleteMapping("{id}")
  Response delete(@PathVariable("id") String id);

  /**
   * 根据id查询报表数据
   *
   * @param id         报表id
   * @param parameters 查询参数
   * @return 报表
   */
  @Operation(summary = "根据id查询报表数据")
  @ApiResponse(responseCode = "200", description = "成功",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ReportResult.class))}
  )
  @Parameter(in = ParameterIn.PATH, name = "id", description = "报表id", schema = @Schema(type = "string"))
  @GetMapping("{id}/data")
  DataResponse<List<Map<String, Object>>> loadDataById(
    @PathVariable("id") String id,
    @RequestParam Map<String, String> parameters
  );

  class ReportPageResult extends DataResponse<Page<ReportEntity>> {

  }

  class ReportResult extends DataResponse<ReportEntity> {

  }
}
