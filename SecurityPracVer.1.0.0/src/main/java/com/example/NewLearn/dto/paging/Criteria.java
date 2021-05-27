package com.example.NewLearn.dto.paging;


import lombok.*;

@Getter
@Setter
@ToString
public class Criteria {

    // 페이징을 위한 멤버 변수
    private int pageNum; // 지금 내가 보고 잇는 페이지 번호
    private int amount;  // 한 페이지에 보여줄 게시글 갯수
    private int displayPageNum;

    // 동적 검색을 위한 멤버 변수
    private String type;        // t, tx, tcw. cw. 이런 식으로 하려고 함 ==> 배열 필요
    private String keyword;

    // 페이징 시 첫 페이지 , 페이지 당 글 갯수 지정
    public Criteria() {
        this.pageNum = 1;
        this.amount = 10;
        this.displayPageNum = 5;
    }


    // mysql의 limit 구문에 사용 되는 메서드
    public int getPageStart(){
        return (pageNum-1) * amount;
    }

    // loop를 돌리 기 위해 배열이 필요하다.
    public String[] getTypeArr() {

    // string 배열이 null이 안나오게 하려고 빈 배열을 준다.
    return  type == null? new String[] {} : type.split("");

    }





/*
  mybatis의 특징은 getter,setter로 동작한다.
  mybatis은 하나의 property다  collection="map"  이라는게 필요하면 객체일떈 getMap을 통해 가져온다.
  page 334
  loop를 돌리기 위해선 배열이 필요해서 get Typearr이라는것을 만든다
  그리고 이걸 split으로 배열을 만든다
  만약에 type이 null 이라면 빈 배열을 만든다. (빈 배열은 null이 안나오게 하려고)

  type == null? new String[] {} : type.split(""); ==> 글자를 하나씩 다 쪼갠다.
  이렇게 안하려면 동적 sql에 not null 체크하는 방법도 있다.

    xml에서  파라미터는 criteria 이다
    그럼 collection을 얻어와야하고 컬렉션은 map이나 배열이다.
    그리고 criteria에 getTypeArr가 있다.그럼 이건 getter setter로 동작하기에 tpyArr로 얻어올 수 있다.
    배열에서는 key가 없기에 item 만 있으면 된다. type에서는 글자마다 쪼개어준 게 들어온다.


    */


}
