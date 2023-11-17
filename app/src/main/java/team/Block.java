package team;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.infinite_stairs.R;

public class Block {
    private boolean isDestroyed;
    private boolean isCloud;
    private ImageView imageView;

    public Block(Context context) {
        isDestroyed = false;
        isCloud = false;
        imageView = new ImageView(context);
        int sizeInDp = 50; // 원하는 크기를 dp 단위로 설정
        float scale = context.getResources().getDisplayMetrics().density;
        int sizeInPixels = (int) (sizeInDp * scale + 0.5f);

        // ImageView 크기 설정
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(sizeInPixels, sizeInPixels);
        imageView.setLayoutParams(params);
        // 추가적인 설정 (크기, 색상 등)
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
        if (destroyed) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
        }
    }

    public void setCloud() {
        isCloud = true;
        // 구름 이미지 설정
        imageView.setImageResource(R.drawable.block_image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
