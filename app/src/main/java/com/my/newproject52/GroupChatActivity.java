package com.my.newproject52;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.*;
import java.io.File;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class GroupChatActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private HashMap<String, Object> map = new HashMap<>();
	private String key = "";
	private String name = "";
	private String keys = "";
	private String groupstr = "";
	private String imagePath = "";
	private String imageName = "";
	
	private ArrayList<HashMap<String, Object>> msgs_lm = new ArrayList<>();
	private ArrayList<String> uids = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user_data = new ArrayList<>();
	
	private LinearLayout linear_bg;
	private LinearLayout tools;
	private ListView listview1;
	private LinearLayout linear_msg_main;
	private LinearLayout tool;
	private LinearLayout linear21;
	private LinearLayout linear_nm;
	private ImageView avatar;
	private LinearLayout l_word;
	private TextView tx_word;
	private LinearLayout linear_n;
	private TextView status;
	private TextView username;
	private LinearLayout linear_message;
	private ImageView im_send;
	private LinearLayout linear1;
	private ImageView im_send_img;
	private EditText tx_message;
	
	private FirebaseAuth fauth;
	private OnCompleteListener<AuthResult> _fauth_create_user_listener;
	private OnCompleteListener<AuthResult> _fauth_sign_in_listener;
	private OnCompleteListener<Void> _fauth_reset_password_listener;
	private OnCompleteListener<Void> fauth_updateEmailListener;
	private OnCompleteListener<Void> fauth_updatePasswordListener;
	private OnCompleteListener<Void> fauth_emailVerificationSentListener;
	private OnCompleteListener<Void> fauth_deleteUserListener;
	private OnCompleteListener<Void> fauth_updateProfileListener;
	private OnCompleteListener<AuthResult> fauth_phoneAuthListener;
	private OnCompleteListener<AuthResult> fauth_googleSignInListener;
	
	private DatabaseReference message = _firebase.getReference("message");
	private ChildEventListener _message_child_listener;
	private Calendar c = Calendar.getInstance();
	private Calendar c_bind = Calendar.getInstance();
	private AlertDialog.Builder dDelete;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference image = _firebase_storage.getReference("image");
	private OnCompleteListener<Uri> _image_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _image_download_success_listener;
	private OnSuccessListener _image_delete_success_listener;
	private OnProgressListener _image_upload_progress_listener;
	private OnProgressListener _image_download_progress_listener;
	private OnFailureListener _image_failure_listener;
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_chat);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear_bg = findViewById(R.id.linear_bg);
		tools = findViewById(R.id.tools);
		listview1 = findViewById(R.id.listview1);
		linear_msg_main = findViewById(R.id.linear_msg_main);
		tool = findViewById(R.id.tool);
		linear21 = findViewById(R.id.linear21);
		linear_nm = findViewById(R.id.linear_nm);
		avatar = findViewById(R.id.avatar);
		l_word = findViewById(R.id.l_word);
		tx_word = findViewById(R.id.tx_word);
		linear_n = findViewById(R.id.linear_n);
		status = findViewById(R.id.status);
		username = findViewById(R.id.username);
		linear_message = findViewById(R.id.linear_message);
		im_send = findViewById(R.id.im_send);
		linear1 = findViewById(R.id.linear1);
		im_send_img = findViewById(R.id.im_send_img);
		tx_message = findViewById(R.id.tx_message);
		fauth = FirebaseAuth.getInstance();
		dDelete = new AlertDialog.Builder(this);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		im_send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!tx_message.getText().toString().trim().equals("")) {
					keys = message.push().getKey();
					c = Calendar.getInstance();
					map = new HashMap<>();
					map.put("id", key);
					map.put("message", tx_message.getText().toString().trim());
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("timestamp", String.valueOf((long)(c.getTimeInMillis())));
					map.put("key", keys);
					map.put("username", name);
					message.child(keys).updateChildren(map);
					tx_message.setText("");
				}
			}
		});
		
		im_send_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		_message_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (((_childValue.containsKey("id") && _childValue.containsKey("message")) && _childValue.containsKey("timestamp")) && _childValue.containsKey("uid")) {
					if ( android.text.TextUtils.isDigitsOnly( _childValue.get("timestamp").toString() )) {
						msgs_lm.add((int)msgs_lm.size(), _childValue);
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						listview1.smoothScrollToPosition((int)(msgs_lm.size()));
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (((_childValue.containsKey("id") && _childValue.containsKey("message")) && _childValue.containsKey("timestamp")) && _childValue.containsKey("uid")) {
					if ( android.text.TextUtils.isDigitsOnly( _childValue.get("timestamp").toString() )) {
						msgs_lm.add((int)msgs_lm.size(), _childValue);
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						listview1.smoothScrollToPosition((int)(msgs_lm.size()));
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				msgs_lm.remove(_childValue);
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
				listview1.smoothScrollToPosition((int)(msgs_lm.size()));
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		message.addChildEventListener(_message_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				uids.add(_childKey);
				user_data.add(_childValue);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					name = _childValue.get("username").toString();
					groupstr = _childValue.get("joined group").toString();
				}
				if (groupstr.contains(key)) {
					linear_msg_main.setVisibility(View.VISIBLE);
				}
				else {
					linear_msg_main.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				uids.add(_childKey);
				user_data.add(_childValue);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					name = _childValue.get("username").toString();
					groupstr = _childValue.get("joined group").toString();
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		_image_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_image_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				keys = message.push().getKey();
				c = Calendar.getInstance();
				map = new HashMap<>();
				map.put("id", key);
				map.put("image", _downloadUrl);
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("timestamp", String.valueOf((long)(c.getTimeInMillis())));
				map.put("key", keys);
				map.put("username", name);
				message.child(keys).updateChildren(map);
				tx_message.setText("");
				SketchwareUtil.showMessage(getApplicationContext(), "image sent");
			}
		};
		
		_image_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_image_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_image_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		fauth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fauth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fauth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fauth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fauth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fauth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		listview1.setAdapter(new Listview1Adapter(msgs_lm));
		key = getIntent().getStringExtra("group id");
		username.setText(getIntent().getStringExtra("group name"));
		getSupportActionBar().setTitle(null);
		
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		_toolbar.setContentInsetStartWithNavigation(0);
		
		_toolbar.setContentInsetsAbsolute(0, 0);
		
		
		
		tools.removeView(tool);
		_toolbar.addView(tool);
		com.google.android.material.appbar.AppBarLayout appBar =
		    (com.google.android.material.appbar.AppBarLayout) _toolbar.getParent();
		appBar.setElevation(3f);
		appBar.setStateListAnimator(null);
		l_word.setVisibility(View.GONE);
		avatar.setVisibility(View.GONE);
		if (getIntent().getStringExtra("group dp").equals("")) {
			l_word.setVisibility(View.VISIBLE);
			tx_word.setText(getIntent().getStringExtra("group name").substring((int)(0), (int)(2)).toUpperCase());
			_Shadow(0, 500, "#65A9E0", l_word);
		}
		else {
			Glide.with(getApplicationContext()).load(Uri.parse(getIntent().getStringExtra("group dp"))).into(avatar);
			avatar.setVisibility(View.VISIBLE);
		}
		_rippleRoundStroke(im_send, "#009688", "#FFFFFF", 500, 0, "#FFFFFF");
		_Shadow(2, 60, "#FFFFFF", linear_message);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				try {
					imagePath = _filePath.get((int)(0));
					imageName = Uri.parse(imagePath).getLastPathSegment();
					image.child(imageName).putFile(Uri.fromFile(new File(imagePath))).addOnFailureListener(_image_failure_listener).addOnProgressListener(_image_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return image.child(imageName).getDownloadUrl();
						}}).addOnCompleteListener(_image_upload_success_listener);
					SketchwareUtil.showMessage(getApplicationContext(), "sending...");
				}catch(Exception e){
					SketchwareUtil.showMessage(getApplicationContext(), e.toString());
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public void _Shadow(final double _sadw, final double _cru, final String _wc, final View _widgets) {
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable();
		wd.setColor(Color.parseColor(_wc));
		wd.setCornerRadius((int)_cru);
		_widgets.setElevation((int)_sadw);
		_widgets.setBackground(wd);
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
		_view.setElevation(5);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.chat, null);
			}
			
			final LinearLayout linear_main = _view.findViewById(R.id.linear_main);
			final LinearLayout linear_item = _view.findViewById(R.id.linear_item);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final LinearLayout linear_msg = _view.findViewById(R.id.linear_msg);
			final TextView tx_sender_message = _view.findViewById(R.id.tx_sender_message);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView tx_msg_time = _view.findViewById(R.id.tx_msg_time);
			
			if (_data.get((int)_position).get("id").toString().equals(getIntent().getStringExtra("group id"))) {
				if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					linear_main.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
					linear_item.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
					tx_message.setAutoLinkMask(android.text.util.Linkify.ALL);
					    tx_message.setLinkTextColor(Color.parseColor("#FFFFFF"));
					
					tx_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
				}
				else {
					linear_main.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
					linear_item.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
					tx_message.setAutoLinkMask(android.text.util.Linkify.ALL);
					    tx_message.setLinkTextColor(Color.parseColor("#008DCD"));
					
					tx_message.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
				}
				c_bind.setTimeInMillis((long)(Double.parseDouble(_data.get((int)_position).get("timestamp").toString())));
				if (_data.get((int)_position).containsKey("image")) {
					linear_item.setVisibility(View.VISIBLE);
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(imageview1);
				}
				else {
					
				}
				if (_data.get((int)_position).containsKey("message")) {
					linear_msg.setVisibility(View.VISIBLE);
					tx_sender_message.setText(_data.get((int)_position).get("message").toString());
				}
				else {
					
				}
				tx_msg_time.setText(new SimpleDateFormat("dd MMM, hh:mm a").format(c_bind.getTime()));
				textview2.setText(_data.get((int)_position).get("username").toString());
				linear_main.setVisibility(View.VISIBLE);
			}
			else {
				linear_main.setVisibility(View.GONE);
			}
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}