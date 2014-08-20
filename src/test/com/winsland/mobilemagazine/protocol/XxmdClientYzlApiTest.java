package com.winsland.mobilemagazine.protocol;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;
import com.winsland.findapp.GlobalConstants;
import com.winsland.findapp.R;
import com.winsland.findapp.bean.ServerResponseResult;
import com.winsland.findapp.bean.appstore.AppDetail;
import com.winsland.findapp.bean.comment.CommentInfo;
import com.winsland.findapp.bean.comment.CommentInfoList;
import com.winsland.findapp.bean.comment.CommentedBlogItem;
import com.winsland.findapp.bean.comment.CommentedBlogList;
import com.winsland.findapp.bean.main.BlogItem;
import com.winsland.findapp.bean.main.CardInfo;
import com.winsland.findapp.bean.main.MainList;
import com.winsland.findapp.bean.main.RankListItem;
import com.winsland.findapp.bean.search.SearchList;
import com.winsland.findapp.bean.search.SearchListAppItem;
import com.winsland.findapp.bean.update.ClientUpdateInfo;
import com.winsland.findapp.bean.updateuserapp.UserAppInfo;
import com.winsland.findapp.bean.user.Register;
import com.winsland.findapp.bean.user.UpLoadPhoto;
import com.winsland.findapp.bean.user.UserInfo;
import com.winsland.findapp.protocol.XxmdClientYzlApi;
import com.winsland.framework.BaseTestCase;
import com.winsland.framework.protocol.BaseProtocol;

import org.apache.http.entity.InputStreamEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@RunWith(RobolectricTestRunner.class)
@Config(manifest="../MobileMagazine/AndroidManifest.xml")
public class XxmdClientYzlApiTest extends BaseTestCase {
	AQuery aq;
	@Before
	public void setUp() throws Exception {
		super.setup();
		aq = new AQuery(Robolectric.application.getApplicationContext());
		// 根据数据库中数据情况，设置一些参数
		GlobalConstants.PhoneModel = "MD188";
		GlobalConstants.MemberId="18";
	}
	@Test
	public void testDefaultRegister() throws Exception {
	enterTestCase();

		AjaxCallback<Register> callback = new AjaxCallback<Register>() {
			@Override
			public void callback(String url, Register result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		// api request from valid id
		XxmdClientYzlApi.defaultRegister("460009876543219","1122223338","MD188","10","658938042056368","12893").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		Register result = callback.getResult();
		Assert.assertTrue(result.user_id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertNotNull(result.device_token);
		Assert.assertNotNull(result.nick_name);
		//Assert.assertNotNull(result.image_url);
		exitTestCase();
	}
	//注意指行前要先修改邮箱，保证每次注册时邮箱不一样
	@Test
	public void testEmailRegister() throws Exception {
		enterTestCase();

		AjaxCallback<Register> callback = new AjaxCallback<Register>() {
			@Override
			public void callback(String url, Register result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		
		String email;
		// api request from valid id
		email = "lfz111@163.com";
		XxmdClientYzlApi.emailRegister(email,"123456","liufengzhen","460009876543211","1122223332","MD188","10","658938042056366","12863").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		Register result = callback.getResult();
		Assert.assertTrue(result.user_id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertNotNull(result.device_token);
		Assert.assertNotNull(result.nick_name);
		//Assert.assertNotNull(result.image_url);
		exitTestCase();
	}

	@Test
	public void testAccountRegister() throws Exception {
		enterTestCase();

		AjaxCallback<Register> callback = new AjaxCallback<Register>() {
			@Override
			public void callback(String url, Register result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		XxmdClientYzlApi.accountRegister(3,"53076351","460009876543216","1112223339","I9100","10","658938042056366","1266").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		Register result = callback.getResult();
		Assert.assertTrue(result.user_id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertNotNull(result.device_token);
		Assert.assertNotNull(result.nick_name);
		//Assert.assertNotNull(result.image_url);
		exitTestCase();
	}
	@Test
	public void testEmailLogin() throws Exception {
		enterTestCase();

		AjaxCallback<Register> callback = new AjaxCallback<Register>() {
			@Override
			public void callback(String url, Register result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		String email = "lfz111@163.com";
		// api request from valid id
		XxmdClientYzlApi.emailLogin(email,"123456","liufengzhen","460009876543211","1122223332","MD188","10","658938042056366","12863").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		Register result = callback.getResult();
		Assert.assertTrue(result.user_id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertNotNull(result.device_token);
		Assert.assertNotNull(result.nick_name);
		//Assert.assertNotNull(result.image_url);
		exitTestCase();
	}
	@Test
	public void testAccountLogin() throws Exception {
		enterTestCase();

		AjaxCallback<Register> callback = new AjaxCallback<Register>() {
			@Override
			public void callback(String url, Register result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from invalid id
		XxmdClientYzlApi.accountLogin(3,"53076351","460009876543216","1112223339","I9100","10","358938042056665","1266").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		Register result = callback.getResult();
		Assert.assertTrue(result.user_id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertNotNull(result.device_token);
		Assert.assertNotNull(result.nick_name);
		//Assert.assertNotNull(result.image_url);
		exitTestCase();
	}
	
	@Test
	public void testResetPassword() throws Exception {
		enterTestCase();

		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		XxmdClientYzlApi.resetPassword("liufengzhen@winsland.cn").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		ServerResponseResult result = callback.getResult();
		Assert.assertNotNull(result.code);
		Assert.assertNotNull(result.description);
		exitTestCase();
	}
	@Test
	public void testGetUserInfo() throws Exception {
		enterTestCase();

		AjaxCallback<UserInfo> callback = new AjaxCallback<UserInfo>() {
			@Override
			public void callback(String url, UserInfo result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		BaseProtocol<UserInfo> request = XxmdClientYzlApi.getUserInfo();
		request.callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		UserInfo result = callback.getResult();
		Assert.assertTrue( result.id > 0);
		Assert.assertNotNull(result.name);
		Assert.assertTrue( result.mark_blog_count >= 0);
		Assert.assertTrue( result.comment_blog_count >= 0);
		Assert.assertNotNull(result.birth);
		Assert.assertNotNull(result.area);
		Assert.assertTrue(result.gender==true||result.gender==false);
		//Assert.assertNotNull( result.icon_url);
		exitTestCase();
	}
	@Test
	public void testUploadUserPhotoFile() throws Exception {
		enterTestCase();
		AjaxCallback<UpLoadPhoto> callback = new AjaxCallback<UpLoadPhoto>() {
			@Override
			public void callback(String url, UpLoadPhoto result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
		}
		};
		
		File uploadFile1 = new File("E:/ere.jpg");
		XxmdClientYzlApi.uploadUserPhoto(uploadFile1,"image/jpeg").callback(callback).execute(aq);
		waitTestComplete();
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		UpLoadPhoto result = callback.getResult();
		Assert.assertNotNull( result.image_url);
		File uploadFile2 = new File("E:/app_icon.png");
		
		XxmdClientYzlApi.uploadUserPhoto(uploadFile2,"image/png").callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		UpLoadPhoto result1 = callback.getResult();
		Assert.assertNotNull( result1.image_url);
		exitTestCase();
	}
	
	@Test
	public void testUploadUserPhotoBitmap() throws Exception {
		enterTestCase();
		AjaxCallback<UpLoadPhoto> callback = new AjaxCallback<UpLoadPhoto>() {
			@Override
			public void callback(String url, UpLoadPhoto result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		BaseProtocol<UpLoadPhoto> protocol = XxmdClientYzlApi.uploadUserPhoto();
		// 创建bitmap
		Bitmap mBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_4444);
		Canvas mCanvas = new Canvas(mBitmap);
		mCanvas.drawColor(Color.RED);
		// 获取参数
		ImageView imageView = new ImageView(aq.getContext());
		imageView.setImageBitmap(mBitmap);
		BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
		Bitmap bitmap = drawable.getBitmap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		InputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
		// 设置参数
		try {
			InputStreamEntity reqEntity = new InputStreamEntity(inputStream,
					baos.size());
			reqEntity.setContentType("image/png");
			protocol.postEntity(reqEntity);
		} catch (Exception e) {
			Log.i("Test", "photo 数据 出错");
		}
		aq.proxy("127.0.0.1", 8888);
		protocol.callback(callback).execute(aq);
		waitTestComplete();
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		UpLoadPhoto result = callback.getResult();
		Assert.assertNotNull( result.image_url);
		exitTestCase();
	}

	@Test
	public void testFeedBackInfo() throws Exception {
		enterTestCase();
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		
		// api request from invalid id
		String dscrinfo= "内容不错";
		XxmdClientYzlApi.feedBackInfo(dscrinfo).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertNotNull( result.description);
		Assert.assertNotNull( result.code);
		exitTestCase();
	}
	@Test
	public void testModifyUserInfo() throws Exception {
		enterTestCase();
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
				@Override
				public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				    System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		XxmdClientYzlApi.modifyUserInfo("lfz","玄幻|穿越|军事","19850203","北京|朝阳","true").callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertNotNull( result.description);
		Assert.assertNotNull( result.code);
		exitTestCase();
	}

	@Test
	public void testClientUpdate() throws Exception{
		enterTestCase();

		AjaxCallback<ClientUpdateInfo> callback = new AjaxCallback<ClientUpdateInfo>() {
			@Override
			public void callback(String url, ClientUpdateInfo result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		XxmdClientYzlApi.clientUpdate().callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		ClientUpdateInfo result = callback.getResult();
		Assert.assertTrue(result.id >0);
		Assert.assertNotNull(result.released_on);
		Assert.assertNotNull(result.version);
		Assert.assertNotNull(result.download_url);
		//Assert.assertNotNull(result.start_url);
		exitTestCase();
	}

	@Test
	public void testGetRankList() throws Exception {
		enterTestCase();

		AjaxCallback<RankListItem[]> callback = new AjaxCallback<RankListItem[]>() {
			@Override
			public void callback(String url, RankListItem[] result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		XxmdClientYzlApi.getRankList().callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		RankListItem[] result = callback.getResult();
		for (int i = 0; i < result.length; i++) {
			RankListItem detail = result[i];
			Assert.assertTrue(detail.id> 0);
			Assert.assertNotNull(detail.name);
			//Assert.assertNotNull(detail.icon_url);
			Assert.assertTrue(detail.blog_count > 0);
			Assert.assertTrue(detail.idx > 0);
		}
		exitTestCase();
	}

	@Test
	public void testGetCardList() throws Exception {
		enterTestCase();

		AjaxCallback<MainList> callback = new AjaxCallback<MainList>() {
			@Override
			public void callback(String url, MainList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		String rankId;
		// api request from invalid id
//		rankId = "123";
//		XxmdClientYzlApi.getCardList(rankId, 0, 25).callback(callback).execute(aq);
//		waitTestComplete();
//
//		Assert.assertTrue(callback.getStatus().getCode() == 200);
//		Assert.assertTrue(callback.getResult() != null && callback.getResult().total > 0);
		// api request from valid id
		rankId = "8004";
		XxmdClientYzlApi.getCardList(rankId).callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null && callback.getResult().total > 0);
		MainList result = callback.getResult();
		Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		BlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			CardInfo detail = result.items.get(i);
			blogItem=detail.blog ;
			Assert.assertTrue(blogItem.blog_id > 0);
			Assert.assertTrue(blogItem.is_single == true ||blogItem.is_single == false);
			Assert.assertNotNull(blogItem.title);
			if(blogItem.is_single == true){
				Assert.assertNotNull(blogItem.category);
				Assert.assertNotNull(blogItem.icon_url);
			}
			Assert.assertNotNull(blogItem.content_url);
			Assert.assertNotNull(blogItem.author);
			Assert.assertNotNull(blogItem.created_on);
			Assert.assertNotNull(blogItem.description);
			Assert.assertNotNull(blogItem.preview_url);
			//Assert.assertNotNull(blogItem.share_info);
			Assert.assertTrue(blogItem.video_cnt >= 0);
			Assert.assertTrue(blogItem.rec_cnt >= 0);
			Assert.assertTrue(blogItem.is_like == true ||blogItem.is_like == false);
			Assert.assertTrue(blogItem.is_mark == true ||blogItem.is_mark == false);
			Assert.assertTrue(blogItem.apps.size() > 0);
			com.winsland.findapp.bean.appstore.AppDetail appDetail;
			for (int j = 0; j < blogItem.apps.size(); j++) {
				appDetail = blogItem.apps.get(j);
				Assert.assertNotNull(appDetail.id);
				//Assert.assertNotNull(appDetail.appName);
				Assert.assertNotNull(appDetail.thumbnail);
				Assert.assertNotNull(appDetail.category);
				Assert.assertNotNull(appDetail.version);
				Assert.assertNotNull(appDetail.updated_on);
				Assert.assertNotNull(appDetail.download_url);
				Assert.assertTrue(appDetail.appSize > 0);
			}
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total > 0);
		exitTestCase();
	}

	@Test
	public void testGetUserRecommendBlogList() throws Exception {
		enterTestCase();

		AjaxCallback<MainList> callback = new AjaxCallback<MainList>() {
			@Override
			public void callback(String url, MainList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		XxmdClientYzlApi.getUserRecommendBlogList().callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		MainList result = callback.getResult();
		Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		BlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			CardInfo detail = result.items.get(i);
			blogItem=detail.blog ;
			Assert.assertTrue(blogItem.blog_id>0);
			Assert.assertTrue(blogItem.is_single == true ||blogItem.is_single == false);
			Assert.assertNotNull(blogItem.title);
			if(blogItem.is_single == true){
				Assert.assertNotNull(blogItem.category);
				Assert.assertNotNull(blogItem.icon_url);
			}
			Assert.assertNotNull(blogItem.content_url);
			Assert.assertNotNull(blogItem.author);
			Assert.assertNotNull(blogItem.created_on);
			Assert.assertNotNull(blogItem.description);
			Assert.assertNotNull(blogItem.preview_url);
			//Assert.assertNotNull(blogItem.share_info);
			Assert.assertTrue(blogItem.video_cnt >= 0);
			Assert.assertTrue(blogItem.rec_cnt >= 0);
			Assert.assertTrue(blogItem.is_like == true ||blogItem.is_like == false);
			Assert.assertTrue(blogItem.is_mark == true ||blogItem.is_mark == false);
			Assert.assertTrue(blogItem.apps.size() > 0);
			com.winsland.findapp.bean.appstore.AppDetail appDetail;
			for (int j = 0; j < blogItem.apps.size(); j++) {
				appDetail = blogItem.apps.get(j);
				Assert.assertNotNull(appDetail.id);
				//Assert.assertNotNull(appDetail.appName);
				Assert.assertNotNull(appDetail.thumbnail);
				Assert.assertNotNull(appDetail.category);
				Assert.assertNotNull(appDetail.version);
				Assert.assertNotNull(appDetail.updated_on);
				Assert.assertNotNull(appDetail.download_url);
				Assert.assertTrue(appDetail.appSize > 0);
			}
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total > 0);
		exitTestCase();
	}

	@Test
	public void testGetNewestList() throws Exception {
		enterTestCase();

		AjaxCallback<MainList> callback = new AjaxCallback<MainList>() {
			@Override
			public void callback(String url, MainList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		XxmdClientYzlApi.getNewestList().callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		MainList result = callback.getResult();
		Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		BlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			CardInfo detail = result.items.get(i);
			blogItem=detail.blog ;
			Assert.assertTrue(blogItem.blog_id>0);
			Assert.assertTrue(blogItem.is_single == true ||blogItem.is_single == false);
			Assert.assertNotNull(blogItem.title);
			if(blogItem.is_single == true){
				Assert.assertNotNull(blogItem.category);
				Assert.assertNotNull(blogItem.icon_url);
			}
			Assert.assertNotNull(blogItem.content_url);
			Assert.assertNotNull(blogItem.author);
			Assert.assertNotNull(blogItem.created_on);
			Assert.assertNotNull(blogItem.description);
			Assert.assertNotNull(blogItem.preview_url);
			//Assert.assertNotNull(blogItem.share_info);
			Assert.assertTrue(blogItem.video_cnt >= 0);
			Assert.assertTrue(blogItem.rec_cnt >= 0);
			Assert.assertTrue(blogItem.video_cnt >= 0);
			Assert.assertTrue(blogItem.is_like == true ||blogItem.is_like == false);
			Assert.assertTrue(blogItem.is_mark == true ||blogItem.is_mark == false);
			Assert.assertTrue(blogItem.apps.size() > 0);
			com.winsland.findapp.bean.appstore.AppDetail appDetail;
			for (int j = 0; j < blogItem.apps.size(); j++) {
				appDetail = blogItem.apps.get(j);
				Assert.assertNotNull(appDetail.id);
				//Assert.assertNotNull(appDetail.appName);
				Assert.assertNotNull(appDetail.thumbnail);
				Assert.assertNotNull(appDetail.category);
				Assert.assertNotNull(appDetail.version);
				Assert.assertNotNull(appDetail.updated_on);
				Assert.assertNotNull(appDetail.download_url);
				Assert.assertTrue(appDetail.appSize > 0);
			}
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total > 0);
		exitTestCase();
	}
	@Test
	public void testSearchApps() throws Exception {
		enterTestCase();

		AjaxCallback<SearchList> callback = new AjaxCallback<SearchList>() {
			@Override
			public void callback(String url, SearchList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		XxmdClientYzlApi.searchApps("","apkblog").callback(callback).execute(aq);
		waitTestComplete();

		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);

		SearchList result = callback.getResult();
		//Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		Assert.assertTrue(result.total > 0 && result.items != null);
		BlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			if(result.items.get(i).app!=null){
				AppDetail appItem = result.items.get(i).app;
				Assert.assertNotNull(appItem.category);
				Assert.assertNotNull(appItem.download_url);
				Assert.assertNotNull(appItem.package_name);
				Assert.assertNotNull(appItem.updated_on);
				Assert.assertNotNull(appItem.version);
			}
			else{
				blogItem = result.items.get(i).blog;
				Assert.assertTrue(blogItem.blog_id > 0) ;
				Assert.assertTrue(blogItem.is_single == true ||blogItem.is_single == false);
				Assert.assertNotNull(blogItem.title);
				if(blogItem.is_single == true){
					Assert.assertNotNull(blogItem.category);
					Assert.assertNotNull(blogItem.icon_url);
				}
				Assert.assertNotNull(blogItem.content_url);
				Assert.assertNotNull(blogItem.author);
				Assert.assertNotNull(blogItem.created_on);
				Assert.assertNotNull(blogItem.description);
				Assert.assertNotNull(blogItem.preview_url);
				//Assert.assertNotNull(blogItem.share_info);
				Assert.assertTrue(blogItem.video_cnt >= 0);
				Assert.assertTrue(blogItem.rec_cnt >= 0);
				Assert.assertTrue(blogItem.is_like == true ||blogItem.is_like == false);
				Assert.assertTrue(blogItem.is_mark == true ||blogItem.is_mark == false);
				Assert.assertTrue(blogItem.apps.size() > 0);
				com.winsland.findapp.bean.appstore.AppDetail appDetail;
				for (int j = 0; j < blogItem.apps.size(); j++) {
					appDetail = blogItem.apps.get(j);
					Assert.assertNotNull(appDetail.id);
					//Assert.assertNotNull(appDetail.appName);
					Assert.assertNotNull(appDetail.thumbnail);
					Assert.assertNotNull(appDetail.category);
					Assert.assertNotNull(appDetail.version);
					Assert.assertNotNull(appDetail.updated_on);
					Assert.assertNotNull(appDetail.download_url);
					Assert.assertTrue(appDetail.appSize > 0);
				}
			}
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total > 0);
		exitTestCase();
	}

	@Test
	public void testAssociatedSearch() throws Exception {
		enterTestCase();

		AjaxCallback<String[]> callback = new AjaxCallback<String[]>() {
			@Override
			public void callback(String url, String[] result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from invalid id
		XxmdClientYzlApi.associatedSearch("书", "apps").callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		String[] result = callback.getResult();
		Assert.assertTrue(result.length > 0 && result != null);
		for (int i = 0; i < result.length; i++) {
			String appItem = result[i];
			Assert.assertNotNull(appItem);
		}
		exitTestCase();
	}
	@Test
	public void testAddMark() throws Exception {
		enterTestCase();
		
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		long blogId =10016;
		XxmdClientYzlApi.addMark(blogId).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}

	@Test
	public void testAddBlogFavorite() throws Exception {
		enterTestCase();
		
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from invalid id
		long blogId =10016;
		XxmdClientYzlApi.addBlogFavorite(blogId).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}
	@Test
	public void testDeleteBlogCollected() throws Exception {
		enterTestCase();
		
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from invalid id
		long blogId =10016;
		XxmdClientYzlApi.deleteBlogCollected(blogId).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}
	@Test
	public void testGetMarkedBlog() throws Exception {
		enterTestCase();
		
		AjaxCallback<MainList> callback = new AjaxCallback<MainList>() {
			@Override
			public void callback(String url, MainList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from invalid id
		XxmdClientYzlApi.getMarkedBlog().callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		MainList result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertTrue(result.total >= 0 && result.items != null && result.total == result.items.size());
		BlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			CardInfo detail = result.items.get(i);
			blogItem=detail.blog ;
			Assert.assertTrue(blogItem.blog_id > 0);
			Assert.assertTrue(blogItem.is_single == true ||blogItem.is_single == false);
			Assert.assertNotNull(blogItem.title);
			if(blogItem.is_single == true){
				Assert.assertNotNull(blogItem.category);
				Assert.assertNotNull(blogItem.icon_url);
			}
			Assert.assertNotNull(blogItem.content_url);
			Assert.assertNotNull(blogItem.author);
			Assert.assertNotNull(blogItem.created_on);
			Assert.assertNotNull(blogItem.description);
			Assert.assertNotNull(blogItem.preview_url);
			//Assert.assertNotNull(blogItem.share_info);
			Assert.assertTrue(blogItem.video_cnt >= 0);
			Assert.assertTrue(blogItem.rec_cnt >= 0);
			Assert.assertTrue(blogItem.is_like == true ||blogItem.is_like == false);
			Assert.assertTrue(blogItem.is_mark == true ||blogItem.is_mark == false);
			Assert.assertTrue(blogItem.apps.size() > 0);
			com.winsland.findapp.bean.appstore.AppDetail appDetail;
			for (int j = 0; j < blogItem.apps.size(); j++) {
				appDetail = blogItem.apps.get(j);
				Assert.assertNotNull(appDetail.id);
				//Assert.assertNotNull(appDetail.appName);
				Assert.assertNotNull(appDetail.thumbnail);
				Assert.assertNotNull(appDetail.category);
				Assert.assertNotNull(appDetail.version);
				Assert.assertNotNull(appDetail.updated_on);
				Assert.assertNotNull(appDetail.download_url);
				Assert.assertTrue(appDetail.appSize > 0);
			}
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total >= 0);
		exitTestCase();
	}
	@Test
	public void testDeleteMark() throws Exception {
		enterTestCase();
	
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
		@Override
		public void callback(String url, ServerResponseResult result, AjaxStatus status) {
			System.out.println("Request=" + url);
			System.out.println("Response=" + new Gson().toJson(result));
			System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
			notifyTestComplete();
			}
		};

		// api request from invalid id
		long blogId =10016;
		XxmdClientYzlApi.deleteMark(blogId).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}
	//标记部分全部通过
	@Test
	public void testAddComment() throws Exception {
		enterTestCase();

		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		long blogId =10017;
		XxmdClientYzlApi.addComment(blogId,"写的很好啊").callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}

	//pass
	@Test
	public void testGetCommentBlogList() throws Exception {
		enterTestCase();
		
		AjaxCallback<CommentedBlogList> callback = new AjaxCallback<CommentedBlogList>() {
			@Override
			public void callback(String url, CommentedBlogList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		XxmdClientYzlApi.getCommentBlogList().callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		CommentedBlogList result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		CommentedBlogItem blogItem;
		for (int i = 0; i < result.items.size(); i++) {
			blogItem = result.items.get(i);
			Assert.assertNotNull(blogItem.title);
			//这两项都是为null
			//Assert.assertNotNull(blogItem.category);
			//Assert.assertNotNull(blogItem.icon_url);
			Assert.assertNotNull(blogItem.created_on);
			Assert.assertNotNull(blogItem.newest_comment);
			Assert.assertTrue(blogItem.comment_count >= 0);
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total >= 0);
		exitTestCase();
	}
	//pass
	@Test
	public void testGetCommentList() throws Exception {
		enterTestCase();
		
		AjaxCallback<CommentInfoList> callback = new AjaxCallback<CommentInfoList>() {
			@Override
			public void callback(String url, CommentInfoList result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		XxmdClientYzlApi.getCommentList(10017).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		CommentInfoList result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertTrue(result.total > 0 && result.items != null && result.total == result.items.size());
		CommentInfo comment;
		for (int i = 0; i < result.items.size(); i++) {
			comment = result.items.get(i);
			Assert.assertNotNull(comment.name);
			//Assert.assertNotNull(comment.icon_url);
			Assert.assertNotNull(comment.comment_on);
			Assert.assertNotNull(comment.comment);
		}
		Assert.assertTrue(result.limit > 0);
		Assert.assertTrue(result.offset >= 0);
		Assert.assertTrue(result.total > 0);
		exitTestCase();
	}
	//pass
	@Test
	public void testDeleteComment() throws Exception {
	enterTestCase();

		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};
		
		// api request from valid id
		long blogId =10017;
		XxmdClientYzlApi.deleteComment(blogId).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}
	//pass
	@Test
	public void testUploadUserApps() throws Exception {
		enterTestCase();
		
		AjaxCallback<ServerResponseResult> callback = new AjaxCallback<ServerResponseResult>() {
			@Override
			public void callback(String url, ServerResponseResult result, AjaxStatus status) {
				System.out.println("Request=" + url);
				System.out.println("Response=" + new Gson().toJson(result));
				System.out.println("Status=" + status.getCode() + "," + status.getMessage() + ",From=" + status.getSource());
				notifyTestComplete();
			}
		};

		// api request from valid id
		List<UserAppInfo> apps=new ArrayList<UserAppInfo>() ;
		UserAppInfo appinfo=new UserAppInfo();
//		appinfo.id=1;
		appinfo.package_name="com.zhj.blastball";
		appinfo.version="1.0";
		apps.add(appinfo);
		appinfo=new UserAppInfo();
//		appinfo.id=2;
		appinfo.package_name="cn.hightech.findfived";
		appinfo.version="1.0";
		apps.add(appinfo);
		XxmdClientYzlApi.uploadUserApps(apps).callback(callback).execute(aq);
		waitTestComplete();
		
		Assert.assertTrue(callback.getStatus().getCode() == 200);
		Assert.assertTrue(callback.getResult() != null);
		
		ServerResponseResult result = callback.getResult();
		Assert.assertTrue( result != null);
		Assert.assertNotNull(result.code);
		Assert.assertNotNull( result.description);
		exitTestCase();
	}
}
