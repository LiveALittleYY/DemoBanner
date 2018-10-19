package demobanner.leeyh.cn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpager;
    private List<Integer> resId=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVpager=findViewById(R.id.viewPager);
        resId.add(R.mipmap.image1);
        resId.add(R.mipmap.image2);
        resId.add(R.mipmap.image3);
        resId.add(R.mipmap.image4);
        mVpager.setAdapter(new MyPagerAdapter());
    }


    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return resId.size();
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            //1.创建view对象
            ImageView iv=new ImageView(getApplicationContext());
            iv.setImageResource(resId.get(position));
            //2.添加到viewPager
            container.addView(iv) ;
            //3.返回view对象
            return iv;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }

}
