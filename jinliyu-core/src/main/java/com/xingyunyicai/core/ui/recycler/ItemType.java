package com.xingyunyicai.core.ui.recycler;

/**
 * 项目名：   DoDoComic
 * 包名：     com.wenyuan.dodo.ui.recycler
 * 文件名：   ItemType
 * 创建者：   DoDo
 * 创建时间： 2017/9/10 2:54
 * 描述：     item布局类型
 */

@SuppressWarnings("unused")
public class ItemType {
    //头部
    public static final int HEADER = 1;
    //尾部
    public static final int FOOTER = 2;
    //分割空间
    public static final int SPACE = 3;
    //广告 占满
    public static final int AD_1 = 4;
    //广告 占1/2
    public static final int AD_2 = 5;
    //轮播图
    public static final int BANNER = 6;
    //内容区域 占满
    public static final int CONTENT_1 = 7;
    //内容区域 占1/2
    public static final int CONTENT_2 = 8;
    //内容区域 占1/3
    public static final int CONTENT_3 = 9;

    //嵌套rv
    public static final int NEST_RECYCLER_VIEW = 10;

    //文本
    public static final int TEXT = 100;
    //图片
    public static final int IMAGE = 101;
    //图文混排
    public static final int IMAGE_TEXT = 102;

    public static final int VERTICAL_MENU_LIST = 103;
    public static final int SINGLE_BIG_IMAGE = 104;

}
