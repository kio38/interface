package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Добавляем  ToDoItem в адаптер
	// Уведомляем обсерверов, что данные изменились

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Очищаем список адаптеров от всех элементов.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Возвращает число элементов ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Возвращает элемент ToDoItem в выбранной позиции

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Получает ID для ToDoItem
	// В данном случае это всего лишь позиция

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Создайте View для ToDoItem в определенной позиции
	// Не забудьте проверить, содержит ли выделенное convertView уже созданное  View
	// перед созданием нового View
	// Рассмотрите использование паттерна ViewHolder чтобы сделать скроллинг более эффективным.
	// См: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// TODO - Получите текущий ToDoItem
		final ToDoItem toDoItem = mItems.get(position);
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// TODO - Заполните View для данного ToDoItem из todo_item.xml
		RelativeLayout itemLayout = (RelativeLayout) convertView;
		if (itemLayout == null) {
			itemLayout = (RelativeLayout) inflater.from(parent.getContext()).inflate(R.layout.todo_item, parent ,false);
			TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
			CheckBox statusCheckBox = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
			TextView priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
			TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
		}
		else
		{
			itemLayout.getTag();
		}
		// Заполните специфичные данные ToDoItem
		// Помните, что данные, которые появляются в этом View
		// соответствуют пользовательскому интерфейсу, описанному
		// в файле макета

		// TODO - Отобразите Title В TextView
		final TextView titleView = null;
		titleView.setText(toDoItem.getTitle());


		// TODO - Установите CheckBox статуса
		final CheckBox statusView = null;
		if(toDoItem.getStatus().equals(ToDoItem.Status.DONE)) {
			statusView.setChecked(true);
		} else {
			statusView.setChecked(false);
		}

		// TODO - Обязательно нужно установить OnCheckedChangeListener,
		// который вызывается когда пользователь переключает чекбокс статуса

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						mItems.get(position).setStatus(isChecked ? ToDoItem.Status.DONE : ToDoItem.Status.NOTDONE);
						notifyDataSetChanged();
					}
				});

		// TODO - Отобразить Приоритет в TextView
		final TextView priorityView = null;
		String prioritet= null;
		switch (toDoItem.getPriority()) {
			case LOW:
				prioritet = mContext.getResources().getString(R.string.priority_low_string);
				break;
			case MED:
				prioritet =  mContext.getResources().getString(R.string.priority_medium_string);
				break;
			case HIGH:
				prioritet =  mContext.getResources().getString(R.string.priority_high_string);
				break;
		}
		priorityView.setText(prioritet);



		// TODO - Отобразить Время и Дату.
		// Подсказка - используйте ToDoItem.FORMAT.format(toDoItem.getDate()) для получения даты и строки
		final TextView dateView = null;
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
		// Возвращает View которое только что создали
		return itemLayout;

	}

}
