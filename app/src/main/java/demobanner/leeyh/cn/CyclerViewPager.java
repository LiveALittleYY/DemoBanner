package demobanner.leeyh.cn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by leeyonghao on 2018/7/23.
 */

public class CyclerViewPager extends ViewPager {

    private Handler handler=new Handler() {
        @Override
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {

        }

        @Override
        public void close() throws SecurityException {

        }

    };
    public CyclerViewPager(@NonNull Context context) {
        super(context);
    }

    public CyclerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(@Nullable PagerAdapter adapter) {
        InnerPagerAdapter innerAdapter=new InnerPagerAdapter(adapter);
        super.setAdapter(innerAdapter);
        addOnPageChangeListener(null);
        setCurrentItem(1);//  ABCD-->DABCDA
        startScroll();
    }

    private void startScroll() {


    }

    @Override
    public void addOnPageChangeListener(@NonNull OnPageChangeListener listener) {
        InnerOnPagerChangeListener innerListener=new InnerOnPagerChangeListener(listener);
        super.addOnPageChangeListener(innerListener);
    }

    class InnerPagerAdapter extends PagerAdapter{

        private PagerAdapter adapter;
        public  InnerPagerAdapter(PagerAdapter adapter){
            this.adapter=adapter;
        }
        @Override
        public int getCount() {
            return adapter.getCount()+2;
        }
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            //position是DABCDA的索引
            if (position==getCount()-1){
                position=0;
            }else if (position==0){
                position=adapter.getCount()-1;
            }else{
                position-=1;
            }

           return adapter.instantiateItem(container, position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return adapter.isViewFromObject(view, object);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            adapter.destroyItem(container, position, object);
        }
    }

    class InnerOnPagerChangeListener implements OnPageChangeListener{

        private OnPageChangeListener listener;

        public InnerOnPagerChangeListener(OnPageChangeListener listener){

            this.listener = listener;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            if (listener!=null){
                listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        private int position;

        @Override
        public void onPageSelected(int position) {

            this.position = position;
            if (listener!=null){
                listener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (position==getAdapter().getCount()-1){
                //A元素
                setCurrentItem(1,false);
            }else if (position==0){
                //D元素
                setCurrentItem(getAdapter().getCount()-2,false);
            }
            if (listener!=null){
                listener.onPageScrollStateChanged(state);
            }
        }
    }

}
