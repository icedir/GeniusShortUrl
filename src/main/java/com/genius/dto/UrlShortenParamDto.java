package com.genius.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * UrlShortenParamDto
 *
 * @author icedir
 * @date 2022-04-06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShortenParamDto implements Serializable {

    /**
     * This url should be a long url
     */
    @NotBlank(message = "url parameter can not be blank!")
    @JsonProperty("url")
    private String url;
}
