package com.example.NewLearn.dto.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PageDTO {

    private int startPage, endPage;
    private boolean prev, next;

    private int total;
    private Criteria cri;

    //  private int pageNum; // 지금 내가 보고 잇는 페이지 번호
//    private int amount;



    public PageDTO(Criteria cri, int total) {
        this.cri = cri;
        this.total = total;

        this.endPage = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;

        this.startPage = endPage - 9;
        this.prev = startPage > 1;
        int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));

        this.endPage = realEnd <= endPage ? realEnd : endPage;
        this.next = endPage < realEnd;

    }

}
