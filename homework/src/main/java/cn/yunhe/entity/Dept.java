package cn.yunhe.entity;

import lombok.Data;

import java.util.List;

@Data
public class Dept {

  private Integer deptno;
  private String dname;
  private String loc;

  private List<Emp> empList;


}
