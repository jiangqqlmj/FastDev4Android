package com.chinaztt.fda.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import com.chinaztt.fda.ui.R;
import com.chinaztt.fda.utils.Log;

/**
 * 自动播放Gallery指示器
 * @author jiangqq
 *
 */
public class FlowIndicator extends View {
	private static  final  String TAG_FLOWINDICATOR="FlowIndicator";
	private int count;
	private float space, radius;
	private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Bitmap bmp_selected, bmp_normal;
	// 选中
	private int seleted = 0;

	public FlowIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FlowIndicator);
		//小圆点数量
		count = a.getInteger(R.styleable.FlowIndicator_count, 4);
		//每个小圆点间隔距离
		space = a.getDimension(R.styleable.FlowIndicator_space, 4);
		//小圆点半径
		radius = a.getDimension(R.styleable.FlowIndicator_indicator_radius, 7);
		//正常 没有选中的图片
		bmp_normal = BitmapFactory.decodeResource(getResources(),
				R.drawable.hui);
		//选中的图片
		bmp_selected = BitmapFactory.decodeResource(getResources(),
				R.drawable.lan);
		a.recycle();
	}

	//当前选中的索引，并且重绘指示器view
	public void setSeletion(int index) {
		this.seleted = index;
		invalidate();
	}
    //设置指示器的数量
	public void setCount(int count) {
		this.count = count;
		invalidate();
	}
    //设置指示器 下一个圆点
	public void next() {
		if (seleted < count - 1)
			seleted++;
		else
			seleted = 0;
		invalidate();
	}
	//设置指示器 前一个圆点
	public void previous() {
		if (seleted > 0)
			seleted--;
		else
			seleted = count - 1;
		invalidate();
	}

	/**
	 * 重写绘制指示器view
	 * @param canvas
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		float width = (getWidth() - ((radius * 2 * count) + (space * (count - 1)))) / 2.f;
		Log.d(TAG_FLOWINDICATOR,"当前选中的为:"+this.seleted);
		for (int i = 0; i < count; i++) {
			if (i == seleted) {
				paint.setStyle(Style.FILL);
				canvas.drawBitmap(bmp_selected, 130+width + getPaddingLeft()
						+ radius + i * (space + radius + radius), 0, null);
			} else {
				paint.setStyle(Style.FILL);
				canvas.drawBitmap(bmp_normal, 130+width + getPaddingLeft() + radius
						+ i * (space + radius + radius), 0, null);
			}
		}
	}

	/**
	 * 进行view大小的测量
	 * @param widthMeasureSpec
	 * @param heightMeasureSpec
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}


	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (getPaddingLeft() + getPaddingRight()
					+ (count * 2 * radius) + (count - 1) * radius + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = (int) (2 * radius + getPaddingTop() + getPaddingBottom() + 1);
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}
}