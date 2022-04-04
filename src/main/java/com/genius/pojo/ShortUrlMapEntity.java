package com.genius.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author icedir
 * @date 2022-03-01
 */
@Data
@AllArgsConstructor
@Builder
@Table("short_url_map")
public class ShortUrlMapEntity {
    /**
     * 自增主键
     */
    @Id
    private Long id;
    /**
     * 长链接
     */
    private String lUrl;
    /**
     * 短链接
     */
    private String sUrl;
    /**
     * 创建时间
     */
    private LocalDateTime createAt;
}
