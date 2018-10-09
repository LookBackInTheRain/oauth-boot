package club.yuit.boot.response;

import static club.yuit.boot.response.HttpStatusAndMsg.exs;

/**
 * @author yuit
 * @create time  2018/3/30 20:37
 * @description
 * @modify
 * @modify time
 */
public final class HttpResponse {

    /**--------------------------BaseResponse-------------------------------------------------*/
    public  static BaseResponse baseResponse(int status) {
        return baseResponse(status, null);
    }

    public  static BaseResponse baseResponse(int status, String msg) {

        if (msg != null) {
            return new BaseResponse(status, msg);
        } else {
            return new BaseResponse(status, exs.get(status));
        }
    }

    public static BaseResponse successResponse(){
        return baseResponse(200);
    }

    public static BaseResponse successResponse(String msg){
        return baseResponse(200,msg);
    }

    /**--------------------------SimpleResponse-------------------------------------------------*/
    public  static SimpleResponse simpleResponse(int status) {
        return simpleResponse(status, null, null);
    }

    public  static SimpleResponse simpleResponse(int status, String msg) {
        return simpleResponse(status, msg, null);
    }

    public  static SimpleResponse simpleResponse(int status, Object data) {
        return simpleResponse(status, null, data);
    }

    public  static SimpleResponse simpleResponse(int status, String msg, Object data) {

        SimpleResponse response = new SimpleResponse();
        response.setStatus(status);
        if (msg != null) {
            response.setMsg(msg);
        } else {
            response.setMsg(exs.get(status));
        }
        response.setItem(data);
        return response;
    }

    public static SimpleResponse successSimpleResponse(Object data){
        return simpleResponse(200,data);
    }

    public static SimpleResponse successSimpleResponse(String msg,Object data){
        return simpleResponse(200,msg,data);
    }

    /**--------------------------PageAndSortResponse-------------------------------------------------*/
    public  static PageAndSortResponse pageAndSortResponse(int status, PageQueryItems query) {
        return pageAndSortResponse(status, null, query);
    }

    public  static PageAndSortResponse pageAndSortResponse(int status, String msg, PageQueryItems query) {
        PageAndSortResponse response = new PageAndSortResponse();
        response.setCurrentPage(query.getCurrentPage());
        response.setPageSize(query.getPageSize());
        response.setCount(query.getCount());
        response.setStatus(status);
        response.setItems(query.getItems());
        if (msg != null) {
            response.setMsg(msg);
        } else {
            response.setMsg(exs.get(status));
        }

        return response;
    }

    public static PageAndSortResponse successPageResponse(PageQueryItems items){
        return pageAndSortResponse(200,items);
    }
    public static PageAndSortResponse successPageResponse(String msg,PageQueryItems items){
        return pageAndSortResponse(200,msg,items);
    }

    /**--------------------------ListResponse-------------------------------------------------*/
    public  static ListResponse listResponse(int status, Items items) {
        return listResponse(status, null, items);
    }

    public  static ListResponse listResponse(int status, String msg, Items items) {
        ListResponse response = new ListResponse();
        response.setCount(items.getCount());
        response.setItems(items.getItems());

        if (msg != null) {
            response.setMsg(msg);
        } else {
            response.setMsg(exs.get(status));
        }

        return response;

    }

    public static ListResponse successListResponse(Items items){
        return listResponse(200,items);
    }

    public static ListResponse successListResponse(String msg,Items items){
        return listResponse(200,msg,items);
    }

}
