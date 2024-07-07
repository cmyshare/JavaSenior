package com.cmyshare;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cmy
 * @version 1.0
 * @date 2024/7/7 16:18
 * @description
 */
public class forTest {
    public static void main(String[] args) {
        List<Category> categoryList = new ArrayList<>();
        Category one = new Category();
        one.setId(1L);
        one.setName("one");
        one.setParentId(null);
        categoryList.add(one);

        Category two = new Category();
        two.setId(2L);
        two.setName("two");
        two.setParentId(1L);
        categoryList.add(two);

        Category three = new Category();
        three.setId(3L);
        three.setName("three");
        three.setParentId(2L);
        categoryList.add(three);

        List<Category> returnList = new ArrayList<>();

        List<Category> sonLinkListById = findSonLinkListById(3L, categoryList, returnList);
        sonLinkListById.stream().map(Category::getName).forEach(System.out::println);

    }

    /**
     * 根据当前id查询当前类目及其子类目信息
     * @param targetId
     * @param categoryList
     * @return
     */
    private static List<Category> findSonLinkListById(Long targetId,List<Category> categoryList,List<Category> returnList){

        //当子节点为空时，退出递归
        if (targetId==null || categoryList.isEmpty()){
            return returnList;
        }

        //找当前id节点
        for (Category category : categoryList) {
            if (targetId.equals(category.getId()) && !returnList.contains(category)){
                returnList.add(category);
            }
        }

        //找子节点
        Long sonNodeId=null;
        for (Category category : categoryList) {
            //找到targetId的子节点退出循环
            if (targetId.equals(category.getParentId())){
                //加入子节点
                returnList.add(category);
                //获取子节点id
                sonNodeId=category.getId();
                break;
            }
        }

        //递归调用
        return findSonLinkListById(sonNodeId,categoryList,returnList);
    }

    /**
     * 根据当前id查询当前类目及其父类目信息
     * @param targetId
     * @param categoryList
     * @return
     */
    private static List<Category> findParentLinkListById(Long targetId,List<Category> categoryList,List<Category> returnList){

        //当子节点为空时，退出递归
        if (targetId==null || categoryList.isEmpty()){
            return returnList;
        }

        //找当前id节点及其父节点
        Long sonNodeId=null;
        for (Category category : categoryList) {
            //找到targetId的节点退出循环
            if (targetId.equals(category.getId())){
                //加入节点
                returnList.add(category);
                //获取父节点id
                sonNodeId=category.getParentId();
                break;
            }
        }

        //递归调用
        return findParentLinkListById(sonNodeId,categoryList,returnList);
    }



    static class Category{
        private Long id;
        private String name;
        private Long parentId;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }
    }
}
