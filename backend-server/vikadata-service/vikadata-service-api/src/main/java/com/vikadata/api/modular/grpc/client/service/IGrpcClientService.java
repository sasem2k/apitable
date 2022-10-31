package com.vikadata.api.modular.grpc.client.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vikadata.integration.grpc.BasicResult;
import com.vikadata.integration.grpc.CorpBizDataDto;
import com.vikadata.integration.grpc.DingTalkUserDto;
import com.vikadata.integration.grpc.NodeCopyRo;
import com.vikadata.integration.grpc.NodeDeleteRo;
import com.vikadata.integration.grpc.TenantInfoResult;
import com.vikadata.social.dingtalk.enums.DingTalkMediaType;
import com.vikadata.social.dingtalk.model.DingTalkAsyncSendCorpMessageResponse;
import com.vikadata.social.dingtalk.model.DingTalkCreateApaasAppRequest;
import com.vikadata.social.dingtalk.model.DingTalkCreateApaasAppResponse;
import com.vikadata.social.dingtalk.model.DingTalkInternalOrderResponse.InAppGoodsOrderVo;
import com.vikadata.social.dingtalk.model.DingTalkSsoUserInfoResponse;
import com.vikadata.social.dingtalk.model.DingTalkUserDetail;
import com.vikadata.social.dingtalk.model.DingTalkUserListResponse.UserPageResult;
import com.vikadata.social.dingtalk.model.UserInfoV2;

/**
 * grpc client interface
 */
public interface IGrpcClientService {
    /**
     * The changesets generated by the replication nodes are generated through the middle layer and notified to the long chain room
     *
     * @param ro request the parameters of the middle layer
     * @return BasicResult
     */
    BasicResult nodeCopyChangeset(NodeCopyRo ro);

    /**
     * Generate changesets generated by deleting nodes through the middle layer, and notify the long chain room
     *
     * @param ro request the parameters of the middle layer
     * @return BasicResult
     */
    BasicResult nodeDeleteChangeset(NodeDeleteRo ro);

    /**
     * Get DingTalk department user list
     *
     * @param suiteId App's suite Id
     * @param authCorpId authorized enterprise id
     * @param deptId Department ID
     * @param cursor Cursor for paging queries
     * @param size Paging Size
     * @return RequestIdResult
     */
    UserPageResult getDingTalkDeptUserList(String suiteId, String authCorpId, String deptId, Integer cursor, Integer size);

    /**
     * DingTalk ISV Application--Get a list of department sub-IDs
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param deptId Parent department ID, pass 1 for the root department
     * @return Sub-department ID list
     */
    List<Long> getDingTalkDepartmentSubIdList(String suiteId, String authCorpId, String deptId);

    /**
     * get user details based on userid
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param userId userid of the user
     * @return DingTalkUserDetailResponse
     */
    DingTalkUserDetail getIsvUserDetailByUserId(String suiteId, String authCorpId, String userId);

    /**
     * Check the status of the authorized enterprise and whether it is authorized
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @return Is it authorized
     */
    Boolean getSocialTenantStatus(String suiteId, String authCorpId);

    /**
     * get user information based on temporary authorization code
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param code temporary authorization code
     * @return UserInfoV2
     */
    UserInfoV2 getUserInfoByCode(String suiteId, String authCorpId, String code);

    /**
     * get background administrator identity information and user information according to the temporary authorization
     * code
     *
     * @param suiteId App's suite Id
     * @param code temporary authorization code
     * @return UserInfoV2
     */
    DingTalkSsoUserInfoResponse getSsoUserInfoByCode(String suiteId, String code);

    /**
     * Send job notification messages using templates
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param agentId Third-party enterprise applications can call the interface to obtain enterprise authorization information to obtain
     * @param templateId message template id
     * @param data Message template dynamic parameter assignment data, indicating that both key and value are in string format
     * @param userIds the maximum length of dingtalk user id is 100
     * @return created asynchronous send task id
     */
    DingTalkAsyncSendCorpMessageResponse sendMessageToUserByTemplateId(String suiteId, String authCorpId, String agentId,
            String templateId, HashMap<String, String> data, List<String> userIds);

    /**
     * upload media files
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param mediaType type
     * @param file file
     * @param fileName file name
     * @return DingTalkMediaCreateResponse
     */
    String uploadMedia(String suiteId, String authCorpId, DingTalkMediaType mediaType, byte[] file, String fileName);

    /**
     * Create apaas application
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param request request parameters
     * @return DingTalkCreateApaasAppResponse
     */
    DingTalkCreateApaasAppResponse createMicroApaasApp(String suiteId, String authCorpId, DingTalkCreateApaasAppRequest request);

    /**
     * Check authorized companies
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @return TenantInfoResult
     */
    TenantInfoResult getSocialTenantInfo(String authCorpId, String suiteId);

    /**
     * Get the SKU page address of in-app purchase products
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param goodsCode product code
     * @param callbackPage Callback page (for URLEncode processing), the micro application is the page URL, and the E application is the page path address
     * @param extendParam parameter
     * @return DingTalkSkuPageResponse
     */
    String getInternalSkuPage(String suiteId, String authCorpId, String goodsCode, String callbackPage,
            String extendParam);

    /**
     * in app purchase order processing completed, Call this API to complete in-app purchase order processing.
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param orderId in app purchase order id
     * @return Boolean
     */
    Boolean internalOrderFinish(String suiteId, String authCorpId, String orderId);

    /**
     * get in app purchase order information
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param orderId in app purchase order id
     * @return DingTalkInternalOrderResponse
     */
    InAppGoodsOrderVo getInternalOrder(String suiteId, String authCorpId, String orderId);

    /**
     * js api dd.config signature generation
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param nonceStr random string
     * @param timestamp timestamp
     * @param url current page link
     * @return String
     */
    String ddConfigSign(String suiteId, String authCorpId, String nonceStr, String timestamp, String url);

    /**
     * Get the number of employees
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param onlyActive Whether to include the number of people who have not activated DingTalk: false: Include the
     * number of people who have not activated DingTalk. true: Only include the number of people who activated DingTalk
     * @return number of workers
     */
    Integer getDingTalkIsvUserCount(String suiteId, String authCorpId, Boolean onlyActive);

    /**
     * Get department user ID
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param deptId department id
     * @return DingTalkDepartmentUserIdListResponse
     */
    List<String> getDingTalkUserIdListByDeptId(String suiteId, String authCorpId, Long deptId);

    /**
     * Recursively given department, query all user information
     *
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param subDeptIds Department ID list
     * @return Map<String, DingTalkUserDto>
     */
    Map<String, DingTalkUserDto> getUserTreeList(String suiteId, String authCorpId, List<String> subDeptIds);

    /**
     * get the event information of the enterprise
     * @param suiteId App's suite Id
     * @param authCorpId Authorized enterprise corpid
     * @param bizTypes event type
     * @return List<CorpBizDataDto>
     */
    List<CorpBizDataDto> getCorpBizDataByBizTypes(String suiteId, String authCorpId, List<Integer> bizTypes);
}
