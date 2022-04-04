package com.genius.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author icedir
 * @date 2022-04-06
 */
@Builder
@AllArgsConstructor
@Data
public class UrlConvertResultVO {
    @NonNull
    private String url;
}
