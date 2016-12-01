package com.groupnine.oss.seller.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.dao.SellerDao;
import com.groupnine.oss.seller.dao.SellerDaoImpl;
import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.entity.GoodsAttr;
import com.groupnine.oss.seller.entity.GoodsImage;
import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.seller.entity.StatisticsData;
import com.groupnine.oss.user.entity.Receiver;

public class SellerServiceImpl implements SellerService {
    SellerDao dao = new SellerDaoImpl();

    public Shop getShopById(int userId) {
        return dao.getShopById(userId);
    }

    public boolean updateShopInfo(Shop shop) {
        return dao.updateShopInfo(shop);
    }

    public boolean updateGoodsInfo(Goods goods) {
        return dao.updateGoodsInfo(goods);
    }

    public boolean sendGoods(Order order) {
        return dao.sendGoods(order);
    }

    public int addNewGoods(Goods goods) {
        return dao.addNewGoods(goods);
    }

    public boolean addGoodsImage(HttpServletRequest request, int goodsId) {
        boolean flag = false;
        int count = -1;
        ArrayList<Part> images;
        try {
            images = (ArrayList<Part>) request.getParts();
            count = images.size();
            for (Part image : images) {
                if (image.getContentType() == null) {
                    continue;
                }
                // System.out.println(image.getContentType());
                InputStream imageInputStream = null;

                if (image != null && image.getSize() != 0) {
                    try {
                        imageInputStream = image.getInputStream();

                        if (imageInputStream != null) {
                            String imagedir = request.getServletContext()
                                    .getInitParameter("imagedir")
                                    + File.separator;
                            // 图片名格式：20161123204206613375.jpg。
                            // 代表 2016-11-23 20:42:06.613 + 3 位 0 - 9 间随机数字
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                            StringBuilder imageName = new StringBuilder(
                                    dateFormat.format(new Date()));
                            Random random = new Random();
                            for (int i = 0; i < 3; ++i) {
                                imageName.append(random.nextInt(10));
                            }
                            imageName.append(".jpg");

                            String targetFile = imagedir + imageName;
                            try {
                                FileUtils.copyInputStreamToFile(imageInputStream,
                                        new File(targetFile));
                                count--;

                                dao.addGoodsImage("/images/goods/" + imageName, goodsId);   // 更新数据库

                                // System.out.println(imagedir);
                                // System.out.println(imageName);
                                // System.out.println(count);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } finally {
                        imageInputStream.close();
                    }
                }
            }
        } catch (IOException | ServletException e3) {
            e3.printStackTrace();
        }
        if (count == 0) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }

    public boolean deleteGoods(int goodsId) {
        return dao.deleteGoods(goodsId);
    }

    public boolean addGoodsAttr(GoodsAttr attr) {
        return dao.addGoodsAttr(attr);
    }

    public boolean updateGoodsAttr(GoodsAttr attr) {
        return dao.updateGoodsAttr(attr);
    }

    public boolean deleteGoodsAttr(int attributeId) {
        return dao.deleteGoodsAttr(attributeId);
    }

    public boolean deleteGoodsImage(int imageId) {
        return dao.deleteGoodsImage(imageId);
    }

    public Goods getGoodsById(int goodsId) {
        return dao.getGoodsById(goodsId);
    }

    public ArrayList<GoodsImage> getImages(int goodsId) {
        return dao.getImages(goodsId);
    }

    public ArrayList<String> getImagesUrl(int goodsId) {
        return dao.getImagesUrl(goodsId);
    }

    public ArrayList<GoodsAttr> getAttrs(int goodsId) {
        return dao.getAttrs(goodsId);
    }

    public ArrayList<StatisticsData> getSingleSales(int days, int goodsId) {
        return dao.getSingleSales(days, goodsId);
    }

    public boolean registerShop(Shop shop) {
        return dao.registerShop(shop);
    }

    public ArrayList<Order> getHistoryOrder(int shopId, int page) {
        return dao.getHistoryOrder(shopId, page);
    }

    public ArrayList<Receiver> getReceiver(int userId) {
        return dao.getReceiver(userId);
    }

    public ArrayList<Order> getUnfinishedOrder(int shopId, int page) {
        return dao.getUnfinishedOrder(shopId, page);
    }

    public Order getOrderDetail(int orderId) {
        return dao.getOrderDetail(orderId);
    }

    public ArrayList<StatisticsData> getTotalSales(int days, int shopId) {
        return dao.getTotalSales(days, shopId);
    }

    public ArrayList<Goods> getGoodsBriefs(int shopId, int page) {
        return dao.getGoodsBriefs(shopId, page);
    }

    public boolean deleteGoodsAttrs(int goodsId) {
        return dao.deleteGoodsAttrs(goodsId);
    }

}