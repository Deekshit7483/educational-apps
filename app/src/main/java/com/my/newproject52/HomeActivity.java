package com.my.newproject52;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class HomeActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String groupstr = "";
	private HashMap<String, Object> m = new HashMap<>();
	private HashMap<String, Object> mm = new HashMap<>();
	private String share = "";
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> totalgroup = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> slist = new ArrayList<>();
	
	private LinearLayout vscroll1;
	private LinearLayout background;
	private LinearLayout linear1;
	private LinearLayout page1;
	private LinearLayout page2;
	private LinearLayout page3;
	private LinearLayout page4;
	private ScrollView vscroll2;
	private LinearLayout linear2;
	private ImageView imageview2;
	private TextView textview100;
	private Button button1;
	private ListView listview3;
	private ListView listview2;
	private LinearLayout linear1099;
	private LinearLayout linear3;
	private LinearLayout l_profile;
	private LinearLayout linear1101;
	private TextView textview99;
	private LinearLayout linear1105;
	private ImageView imageview88;
	private LinearLayout linear1073;
	private LinearLayout linear9;
	private LinearLayout linear10;
	private LinearLayout linear1075;
	private ImageView imageview89;
	private TextView name;
	private ImageView imageview84;
	private ImageView imageview67;
	private TextView clg;
	private TextView textview87;
	private TextView textview88;
	private LinearLayout back2;
	private LinearLayout back;
	private LinearLayout back3;
	private LinearLayout back4;
	private LinearLayout b2;
	private LinearLayout above2;
	private ImageView img2;
	private TextView t2;
	private LinearLayout b1;
	private LinearLayout above;
	private ImageView amg1;
	private TextView t1;
	private LinearLayout b3;
	private LinearLayout above3;
	private ImageView img3;
	private TextView t3;
	private LinearLayout b4;
	private LinearLayout above4;
	private ImageView img4;
	private TextView t4;
	
	private Intent i = new Intent();
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
	
	private DatabaseReference groups = _firebase.getReference("groups");
	private ChildEventListener _groups_child_listener;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		vscroll1 = findViewById(R.id.vscroll1);
		background = findViewById(R.id.background);
		linear1 = findViewById(R.id.linear1);
		page1 = findViewById(R.id.page1);
		page2 = findViewById(R.id.page2);
		page3 = findViewById(R.id.page3);
		page4 = findViewById(R.id.page4);
		vscroll2 = findViewById(R.id.vscroll2);
		linear2 = findViewById(R.id.linear2);
		imageview2 = findViewById(R.id.imageview2);
		textview100 = findViewById(R.id.textview100);
		button1 = findViewById(R.id.button1);
		listview3 = findViewById(R.id.listview3);
		listview2 = findViewById(R.id.listview2);
		linear1099 = findViewById(R.id.linear1099);
		linear3 = findViewById(R.id.linear3);
		l_profile = findViewById(R.id.l_profile);
		linear1101 = findViewById(R.id.linear1101);
		textview99 = findViewById(R.id.textview99);
		linear1105 = findViewById(R.id.linear1105);
		imageview88 = findViewById(R.id.imageview88);
		linear1073 = findViewById(R.id.linear1073);
		linear9 = findViewById(R.id.linear9);
		linear10 = findViewById(R.id.linear10);
		linear1075 = findViewById(R.id.linear1075);
		imageview89 = findViewById(R.id.imageview89);
		name = findViewById(R.id.name);
		imageview84 = findViewById(R.id.imageview84);
		imageview67 = findViewById(R.id.imageview67);
		clg = findViewById(R.id.clg);
		textview87 = findViewById(R.id.textview87);
		textview88 = findViewById(R.id.textview88);
		back2 = findViewById(R.id.back2);
		back = findViewById(R.id.back);
		back3 = findViewById(R.id.back3);
		back4 = findViewById(R.id.back4);
		b2 = findViewById(R.id.b2);
		above2 = findViewById(R.id.above2);
		img2 = findViewById(R.id.img2);
		t2 = findViewById(R.id.t2);
		b1 = findViewById(R.id.b1);
		above = findViewById(R.id.above);
		amg1 = findViewById(R.id.amg1);
		t1 = findViewById(R.id.t1);
		b3 = findViewById(R.id.b3);
		above3 = findViewById(R.id.above3);
		img3 = findViewById(R.id.img3);
		t3 = findViewById(R.id.t3);
		b4 = findViewById(R.id.b4);
		above4 = findViewById(R.id.above4);
		img4 = findViewById(R.id.img4);
		t4 = findViewById(R.id.t4);
		fauth = FirebaseAuth.getInstance();
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), CreateGroupActivity.class);
				startActivity(i);
			}
		});
		
		textview100.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), FilesActivity.class);
				startActivity(i);
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), AddEventActivity.class);
				startActivity(i);
			}
		});
		
		listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				i.setClass(getApplicationContext(), GroupChatActivity.class);
				i.putExtra("group name", list.get((int)_position).get("group name").toString());
				i.putExtra("group id", list.get((int)_position).get("key").toString());
				i.putExtra("group dp", list.get((int)_position).get("group dp").toString());
				i.putExtra("admin", list.get((int)_position).get("uid").toString());
				startActivity(i);
			}
		});
		
		imageview88.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(HomeActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
				
				TextView t3 = (TextView) bottomSheetView.findViewById(R.id.t3);
				
				TextView t4 = (TextView) bottomSheetView.findViewById(R.id.t4);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				ImageView i2 = (ImageView) bottomSheetView.findViewById(R.id.i2);
				
				ImageView i3 = (ImageView) bottomSheetView.findViewById(R.id.i3);
				
				ImageView i4 = (ImageView) bottomSheetView.findViewById(R.id.i4);
				
				ImageView i5 = (ImageView) bottomSheetView.findViewById(R.id.i5);
				
				LinearLayout bg1 = (LinearLayout) bottomSheetView.findViewById(R.id.bg1);
				
				LinearLayout linear2 = (LinearLayout) bottomSheetView.findViewById(R.id.linear2);
				
				LinearLayout linear3 = (LinearLayout) bottomSheetView.findViewById(R.id.linear3);
				
				LinearLayout linear4 = (LinearLayout) bottomSheetView.findViewById(R.id.linear4);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				_rippleRoundStroke(bg1, "#FFFFFF", "#E0E0E0", 0, 0, "#000000");
				_rippleRoundStroke(linear2, "#FFFFFF", "#E0E0E0", 0, 0, "#000000");
				_rippleRoundStroke(linear3, "#FFFFFF", "#E0E0E0", 0, 0, "#000000");
				_rippleRoundStroke(linear4, "#FFFFFF", "#E0E0E0", 0, 0, "#000000");
				_rippleRoundStroke(i5, "#FFFFFF", "#E0E0E0", 50, 0, "#000000");
				bg1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						
						i.setClass(getApplicationContext(), EditProfileActivity.class);
						startActivity(i);
					}
				});
				linear2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						
					}
				});
				linear3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						
						FirebaseAuth.getInstance().signOut();
						i.setClass(getApplicationContext(), LoginActivity.class);
						startActivity(i);
					}
				});
				linear4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						share = "https://www.vidyavan.in/?type=user&id=".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
						Intent i = new Intent(android.content.Intent.ACTION_SEND); i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT,share); startActivity(Intent.createChooser(i,"Choose one.."));
					}
				});
				i5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
			}
		});
		
		above2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(t2, "ScaleY", 0, 1);
				anim.setInterpolator(new BounceInterpolator()); 
				anim.setDuration(1000);
				anim.start();
				above3.setBackgroundColor(Color.TRANSPARENT);
				above.setBackgroundColor(Color.TRANSPARENT);
				above4.setBackgroundColor(Color.TRANSPARENT);
				_rippleRoundStroke(above2, "#3affffff", "#40FFFFFF", 20, 0, "#000000");
				t2.setTextColor(0xFFFFFFFF);
				t3.setTextColor(0xFFE0E0E0);
				t1.setTextColor(0xFFE0E0E0);
				t4.setTextColor(0xFFE0E0E0);
				_Icon_Colour(img2, "#ffffff");
				_Icon_Colour(img3, "#e0e0e0");
				_Icon_Colour(amg1, "#e0e0e0");
				_Icon_Colour(img4, "#e0e0e0");
				t2.setVisibility(View.VISIBLE);
				t1.setVisibility(View.GONE);
				t3.setVisibility(View.GONE);
				t4.setVisibility(View.GONE);
				page1.setVisibility(View.VISIBLE);
				page2.setVisibility(View.GONE);
				page3.setVisibility(View.GONE);
				page4.setVisibility(View.GONE);
			}
		});
		
		above.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(t1, "ScaleY", 0, 1);
				anim.setInterpolator(new BounceInterpolator()); 
				anim.setDuration(1000);
				anim.start();
				above2.setBackgroundColor(Color.TRANSPARENT);
				above3.setBackgroundColor(Color.TRANSPARENT);
				above4.setBackgroundColor(Color.TRANSPARENT);
				_rippleRoundStroke(above, "#3affffff", "#40FFFFFF", 20, 0, "#000000");
				t1.setTextColor(0xFFFFFFFF);
				t2.setTextColor(0xFFE0E0E0);
				t3.setTextColor(0xFFE0E0E0);
				t4.setTextColor(0xFFE0E0E0);
				_Icon_Colour(amg1, "#ffffff");
				_Icon_Colour(img2, "#e0e0e0");
				_Icon_Colour(img3, "#e0e0e0");
				_Icon_Colour(img4, "#e0e0e0");
				t1.setVisibility(View.VISIBLE);
				t3.setVisibility(View.GONE);
				t2.setVisibility(View.GONE);
				t4.setVisibility(View.GONE);
				page1.setVisibility(View.GONE);
				page2.setVisibility(View.VISIBLE);
				page3.setVisibility(View.GONE);
				page4.setVisibility(View.GONE);
			}
		});
		
		above3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(t3, "ScaleY", 0, 1);
				anim.setInterpolator(new BounceInterpolator()); 
				anim.setDuration(1000);
				anim.start();
				above2.setBackgroundColor(Color.TRANSPARENT);
				above.setBackgroundColor(Color.TRANSPARENT);
				above4.setBackgroundColor(Color.TRANSPARENT);
				_rippleRoundStroke(above3, "#3affffff", "#3affffff", 20, 0, "#000000");
				t3.setTextColor(0xFFFFFFFF);
				t2.setTextColor(0xFFE0E0E0);
				t1.setTextColor(0xFFE0E0E0);
				t4.setTextColor(0xFFE0E0E0);
				_Icon_Colour(img3, "#ffffff");
				_Icon_Colour(img2, "#e0e0e0");
				_Icon_Colour(amg1, "#e0e0e0");
				_Icon_Colour(img4, "#e0e0e0");
				t3.setVisibility(View.VISIBLE);
				t1.setVisibility(View.GONE);
				t2.setVisibility(View.GONE);
				t4.setVisibility(View.GONE);
				page1.setVisibility(View.GONE);
				page2.setVisibility(View.GONE);
				page3.setVisibility(View.VISIBLE);
				page4.setVisibility(View.GONE);
			}
		});
		
		above4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(t4, "ScaleY", 0, 1);
				anim.setInterpolator(new BounceInterpolator()); 
				anim.setDuration(1000);
				anim.start();
				above2.setBackgroundColor(Color.TRANSPARENT);
				above.setBackgroundColor(Color.TRANSPARENT);
				above3.setBackgroundColor(Color.TRANSPARENT);
				_rippleRoundStroke(above4, "#3affffff", "#40FFFFFF", 20, 0, "#000000");
				t4.setTextColor(0xFFFFFFFF);
				t2.setTextColor(0xFFE0E0E0);
				t1.setTextColor(0xFFE0E0E0);
				t3.setTextColor(0xFFE0E0E0);
				_Icon_Colour(img4, "#ffffff");
				_Icon_Colour(img2, "#e0e0e0");
				_Icon_Colour(amg1, "#e0e0e0");
				_Icon_Colour(img3, "#e0e0e0");
				t4.setVisibility(View.VISIBLE);
				t1.setVisibility(View.GONE);
				t2.setVisibility(View.GONE);
				t3.setVisibility(View.GONE);
				page1.setVisibility(View.GONE);
				page2.setVisibility(View.GONE);
				page3.setVisibility(View.GONE);
				page4.setVisibility(View.VISIBLE);
			}
		});
		
		_groups_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				groups.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						list = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								list.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						listview2.setAdapter(new Listview2Adapter(list));
						((BaseAdapter)listview2.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		groups.addChildEventListener(_groups_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					groupstr = _childValue.get("joined group").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
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
		ObjectAnimator anim = ObjectAnimator.ofFloat(t2, "ScaleY", 0, 1);
		anim.setInterpolator(new BounceInterpolator()); 
		anim.setDuration(1000);
		anim.start();
		above3.setBackgroundColor(Color.TRANSPARENT);
		above.setBackgroundColor(Color.TRANSPARENT);
		above4.setBackgroundColor(Color.TRANSPARENT);
		_rippleRoundStroke(above2, "#3affffff", "#40FFFFFF", 20, 0, "#000000");
		t2.setTextColor(0xFFFFFFFF);
		t3.setTextColor(0xFFE0E0E0);
		t1.setTextColor(0xFFE0E0E0);
		t4.setTextColor(0xFFE0E0E0);
		_Icon_Colour(img2, "#ffffff");
		_Icon_Colour(img3, "#e0e0e0");
		_Icon_Colour(amg1, "#e0e0e0");
		_Icon_Colour(img4, "#e0e0e0");
		t2.setVisibility(View.VISIBLE);
		t1.setVisibility(View.GONE);
		t3.setVisibility(View.GONE);
		t4.setVisibility(View.GONE);
		page1.setVisibility(View.VISIBLE);
		page2.setVisibility(View.GONE);
		page3.setVisibility(View.GONE);
		page4.setVisibility(View.GONE);
		_borderline(l_profile, "#ffffff", 1.5d, "#9e9e9e", 20);
		linear1101.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1.2d, 0xFF673AB7, 0xFFEDE7F6));
		linear1099.setBackgroundColor(0xFFF5F5F5);
		linear1101.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)20, (int)1.2d, 0xFF673AB7, 0xFFEDE7F6));
		{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
				0xFFFFFFFF,
				0xFFFFFFFF,
			});
			gd.setCornerRadius(20);
			l_profile.setBackgroundDrawable(gd);
			l_profile.setElevation(8);};
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		users.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				totalgroup = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						totalgroup.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				if (groupstr.length() > 0) {
					totalgroup = new Gson().fromJson("[".concat(groupstr.concat("]")), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					listview3.setAdapter(new Listview3Adapter(totalgroup));
					((BaseAdapter)listview3.getAdapter()).notifyDataSetChanged();
				}
				else {
					
				}
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
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
	
	
	public void _Icon_Colour(final ImageView _iconview, final String _colour) {
		_iconview.getDrawable().setColorFilter(Color.parseColor(_colour), PorterDuff.Mode.SRC_IN);
	}
	
	
	public void _BorderLiner(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _borderline(final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	public class Listview3Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview3Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.group, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final Button button1 = _view.findViewById(R.id.button1);
			
			button1.setVisibility(View.GONE);
			textview1.setText(_data.get((int)_position).get("group name").toString());
			{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
					0xFF9E9E9E,
					0xFF9E9E9E,
				});
				gd.setCornerRadius(15);
				linear1.setBackgroundDrawable(gd);
				linear1.setElevation(15);};
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), GroupChatActivity.class);
					i.putExtra("group name", _data.get((int)_position).get("group name").toString());
					i.putExtra("group id", _data.get((int)_position).get("group id").toString());
					i.putExtra("admin", _data.get((int)_position).get("uid").toString());
					i.putExtra("group dp", _data.get((int)_position).get("group dp").toString());
					startActivity(i);
				}
			});
			
			return _view;
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.group, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final Button button1 = _view.findViewById(R.id.button1);
			
			if (_data.get((int)_position).containsKey("group name")) {
				textview1.setText(_data.get((int)_position).get("group name").toString());
			}
			{android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.RIGHT_LEFT, new int[]{
					0xFF9E9E9E,
					0xFF9E9E9E,
				});
				gd.setCornerRadius(15);
				linear1.setBackgroundDrawable(gd);
				linear1.setElevation(15);};
			if (groupstr.contains(_data.get((int)_position).get("key").toString())) {
				button1.setText("Open");
			}
			else {
				button1.setText("Join now");
			}
			button1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (groupstr.contains(_data.get((int)_position).get("key").toString())) {
						i.setClass(getApplicationContext(), GroupChatActivity.class);
						i.putExtra("group name", _data.get((int)_position).get("group name").toString());
						i.putExtra("group id", _data.get((int)_position).get("key").toString());
						i.putExtra("group dp", _data.get((int)_position).get("group dp").toString());
						i.putExtra("admin", _data.get((int)_position).get("uid").toString());
						startActivity(i);
					}
					else {
						final com.google.android.material.bottomsheet.BottomSheetDialog a = new com.google.android.material.bottomsheet.BottomSheetDialog(HomeActivity.this);
						View lay = getLayoutInflater().inflate(R.layout.dialog, null);
						a.setContentView(lay);
						a.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
						final TextView ok  = (TextView)lay.findViewById(R.id.ok);
						final TextView cancel  = (TextView)lay.findViewById(R.id.cancel);
						final TextView textview3  = (TextView)lay.findViewById(R.id.textview3);
						final TextView textview4  = (TextView)lay.findViewById(R.id.textview4);
						final LinearLayout v1  = (LinearLayout)lay.findViewById(R.id.v1);
						final ImageView image  = (ImageView)lay.findViewById(R.id.image);
						if (false) {
								
								android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
								gd.setColor(0xFFFFFFFF);
								gd.setCornerRadius((int)30);
								gd.setStroke((int)5,0xFFF44336);
								if (Build.VERSION.SDK_INT >= 21){
										v1.setElevation((int)7);}
								
								android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
								
								android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
								v1.setClickable(true);
								v1.setBackground(ripdrb);
								
						} else {
								
								android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
								gd.setColor(0xFFFFFFFF);
								gd.setCornerRadius((int)30);
								gd.setStroke((int)5,0xFFF44336);
								v1.setBackground(gd);
								if (Build.VERSION.SDK_INT >= 21){
										v1.setElevation((int)7);}
								
						}
						a.setCancelable(false);
						textview3.setText("Do you want to join this group? ");
						textview4.setText("StudyCompanion");
						ok.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFF44336));
						cancel.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)30, 0xFFF44336));
						image.setElevation((float)100);
						ok.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										 		 
								i.setClass(getApplicationContext(), GroupChatActivity.class);
								m.put("uid", _data.get((int)_position).get("uid").toString());
								m.put("group id", _data.get((int)_position).get("key").toString());
								m.put("group name", _data.get((int)_position).get("group name").toString());
								m.put("group dp", _data.get((int)_position).get("group dp").toString());
								slist.add(m);
								if (groupstr.trim().length() > 0) {
									mm = new HashMap<>();
									mm.put("joined group", groupstr.concat(",").concat(new Gson().toJson(m)));
									users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mm);
									mm.clear();
								}
								else {
									mm = new HashMap<>();
									mm.put("joined group", new Gson().toJson(m));
									users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(mm);
									mm.clear();
								}
								i.putExtra("group name", _data.get((int)_position).get("group name").toString());
								i.putExtra("group id", _data.get((int)_position).get("key").toString());
								i.putExtra("group dp", _data.get((int)_position).get("group dp").toString());
								i.putExtra("admin", _data.get((int)_position).get("uid").toString());
								startActivity(i);
							}
						});
						cancel.setOnClickListener(new View.OnClickListener(){
									@Override
									public void onClick(View _view){
										a.dismiss();		 
									}
						});
						a.show();
					}
				}
			});
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					i.setClass(getApplicationContext(), GroupChatActivity.class);
					i.putExtra("group name", _data.get((int)_position).get("group name").toString());
					i.putExtra("group id", _data.get((int)_position).get("key").toString());
					i.putExtra("group dp", _data.get((int)_position).get("group dp").toString());
					i.putExtra("admin", _data.get((int)_position).get("uid").toString());
					startActivity(i);
				}
			});
			
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