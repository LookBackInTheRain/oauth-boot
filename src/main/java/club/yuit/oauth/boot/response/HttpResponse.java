package club.yuit.oauth.boot.response;

import static club.yuit.oauth.boot.response.HttpStatusAndMsg.exs;

/**
 * @author yuit
 * @date  2018/3/30 20:37
 *
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

    public  static <T> SimpleResponse<T> simpleResponse(int status, String msg) {
        return simpleResponse(status, msg, null);
    }

    public  static <T> SimpleResponse<T> simpleResponse(int status, T data) {
        return simpleResponse(status, null, data);
    }

    public  static <T> SimpleResponse<T> simpleResponse(int status, String msg, T data) {

        SimpleResponse<T> response = new SimpleResponse<>();
        response.setStatus(status);
        if (msg != null) {
            response.setMsg(msg);
        } else {
            response.setMsg(exs.get(status));
        }
        response.setItem(data);
        return response;
    }

    public static <T> SimpleResponse<T> successSimpleResponse(T data){
        return simpleResponse(200,data);
    }

    public static <T> SimpleResponse<T> successSimpleResponse(String msg,T data){
        return simpleResponse(200,msg,data);
    }

    /**--------------------------PageAndSortResponse-------------------------------------------------*/
    public  static <T> PageAndSortResponse<T> pageAndSortResponse(int status, PageQueryItems<T> query) {
        return pageAndSortResponse(status, null, query);
    }

    public  static <T> PageAndSortResponse<T> pageAndSortResponse(int status, String msg, PageQueryItems<T> query) {
        PageAndSortResponse<T> response = new PageAndSortResponse<>();
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

    public static <T> PageAndSortResponse<T> successPageResponse(PageQueryItems<T> items){
        return pageAndSortResponse(200,items);
    }
    public static <T> PageAndSortResponse<T> successPageResponse(String msg,PageQueryItems<T> items){
        return pageAndSortResponse(200,msg,items);
    }

    /**--------------------------ListResponse-------------------------------------------------*/
    public  static <T> ListResponse<T> listResponse(int status, Items<T> items) {
        return listResponse(status, null, items);
    }

    public  static <T> ListResponse<T> listResponse(int status, String msg, Items<T> items) {
        ListResponse<T> response = new ListResponse<>();
        response.setCount(items.getCount());
        response.setItems(items.getItems());

        if (msg != null) {
            response.setMsg(msg);
        } else {
            response.setMsg(exs.get(status));
        }

        return response;

    }

    public static <T> ListResponse<T> successListResponse(Items<T> items){
        return listResponse(200,items);
    }

    public static <T> ListResponse<T> successListResponse(String msg,Items<T> items){
        return listResponse(200,msg,items);
    }

}
