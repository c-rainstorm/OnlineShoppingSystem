package com.groupnine.oss.seller.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.entity.GoodsAttr;
import com.groupnine.oss.seller.entity.GoodsImage;
import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.seller.entity.StatisticsData;
import com.groupnine.oss.user.entity.Receiver;

public interface SellerService {
    /****************************************
     * 通过userId获取店铺信息
     * 
     * @param userId
     *            用户ID
     * @return shop 店铺
     */
    Shop getShopById(int userId);

    /****************************************
     * 更新店铺信息
     * 
     * @param shop
     *            商家
     * @return true -- 成功 false -- 失败
     */
    boolean updateShopInfo(Shop shop);

    /****************************************
     * 更改商品名称和描述
     * 
     * @param goods
     * 
     * @return true -- 成功 false -- 失败
     */
    boolean updateGoodsInfo(Goods goods);

    /****************************************
     * 更新订单状态----送货
     * 
     * @param order
     *            订单
     * @return true -- 成功 false -- 失败
     */
    boolean sendGoods(Order order);

    /****************************************
     * 添加新商品
     * 
     * @param goods
     *            商品
     * @return 新添加的商品id(>=1)或-1(失败)
     */
    int addNewGoods(Goods goods);

    /****************************************
     * 上传图片
     * 
     * @param request
     *            带图片的请求 goodsId 要插入图片的商品ID
     * @return true -- 成功 false -- 失败
     */
    boolean addGoodsImage(HttpServletRequest request, int goodsId);

    /****************************************
     * 删除商品
     * 
     * @param goodsId
     *            商品id
     * @return true -- 成功 false -- 失败
     */
    boolean deleteGoods(int goodsId);

    /****************************************
     * 添加商品属性
     * 
     * @param Attr
     *            属性
     * @return true -- 成功 false -- 失败
     */
    boolean addGoodsAttr(GoodsAttr attr);

    /****************************************
     * 修改商品属性
     * 
     * @param Attr
     *            属性
     * @return true -- 成功 false -- 失败
     */
    boolean updateGoodsAttr(GoodsAttr attr);

    /****************************************
     * 删除商品属性
     * 
     * @param attributeId
     *            属性id
     * @return true -- 成功 false -- 失败
     */
    boolean deleteGoodsAttr(int attributeId);

    /****************************************
     * 删除图片
     * 
     * @param imageId
     *            图片id
     * @return true -- 成功 false -- 失败
     */
    boolean deleteGoodsImage(int imageId);

    /****************************************
     * 通过goodsId获取商品信息
     * 
     * @param goodsId
     *            商品ID
     * @return goods 商品
     */
    Goods getGoodsById(int goodsId);

    /****************************************
     * 通过goodsId获取商品图片的集合
     * 
     * @param goodsId
     *            商品ID
     * @return ArrayList<GoodsImage>
     */
    ArrayList<GoodsImage> getImages(int goodsId);

    /****************************************
     * 通过goodsId获取商品图片地址的集合
     * 
     * @param goodsId
     *            商品ID
     * @return ArrayList<String>
     */
    ArrayList<String> getImagesUrl(int goodsId);

    /****************************************
     * 通过goodsId获取商品属性的集合
     * 
     * @param goodsId
     *            商品ID
     * @return ArrayList<GoodsAttr>
     */
    ArrayList<GoodsAttr> getAttrs(int goodsId);

    /****************************************
     * 获得某个商品的每日销量
     * 
     * @param
     * 
     * @return ArrayList<StatisticsData>
     */
    ArrayList<StatisticsData> getSingleSales(int days, int goodsId);

    /****************************************
     * 注册店铺
     * 
     * @param shop
     *            商家
     * @return true -- 成功 false -- 失败
     */
    boolean registerShop(Shop shop);

    /****************************************
     * 通过shopId获得历史订单
     * 
     * @param shopId
     *            page
     * 
     * @return ArrayList<Order>
     */
    ArrayList<Order> getHistoryOrder(int shopId, int page);

    /****************************************
     * 通过userId获得收货人
     * 
     * @param userId
     * 
     * @return ArrayList<Receiver>
     */
    ArrayList<Receiver> getReceiver(int userId);

    /****************************************
     * 通过shopId获得历史订单
     * 
     * @param shopId
     *            page
     * 
     * @return ArrayList<Order>
     */
    ArrayList<Order> getUnfinishedOrder(int shopId, int page);

    /****************************************
     * 通过orderId获得订单详情
     * 
     * @param orderId
     * 
     * @return Order
     */
    Order getOrderDetail(int orderId);

    /****************************************
     * 获得商店的每日销量
     * 
     * @param
     * 
     * @return ArrayList<StatisticsData>
     */
    ArrayList<StatisticsData> getTotalSales(int days, int shopId);

    /****************************************
     * 通过shopId获得商品简讯
     * 
     * @param shopId
     *            page
     * 
     * @return ArrayList<Goods>
     */
    ArrayList<Goods> getGoodsBriefs(int shopId, int page);

    boolean deleteGoodsAttrs(int goodsId);

}