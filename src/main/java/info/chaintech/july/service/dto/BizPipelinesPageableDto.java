package info.chaintech.july.service.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BizPipelinesPageableDto {
    private List<BizLineDto> bizLineDtoList = new ArrayList<>();
    private long totalElements;

    public void addBizLineDto(BizLineDto bizLineDto) {
        this.bizLineDtoList.add(bizLineDto);
    }
}
