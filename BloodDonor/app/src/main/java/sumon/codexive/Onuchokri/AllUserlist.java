package sumon.codexive.Onuchokri;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.List;

public class AllUserlist extends ArrayAdapter<User> {
    private Activity context;
    List<User> users;



    public AllUserlist(Activity context, List<User> users) {
        super(context, R.layout.list_layout, users);
        this.context = context;
        this.users = users;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint("ViewHolder") View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

       // TextView textViewName =  listViewItem.findViewById(R.id.textView1ForList);
        TextView textViewPhone = listViewItem.findViewById(R.id.textView2ForList);
        TextView textViewDate = listViewItem.findViewById(R.id.textView3ForList);
     //   TextView textViewBlood_city = listViewItem.findViewById(R.id.textView3ForList);

        User user = users.get(position);
      //  textViewName.setText(user.getName());
        textViewPhone.setText(user.getPhone());
        String string = user.getName_phone_date();
        String[] parts = string.split(",");
        String part1 = parts[0]; // 004
        String part2 = parts[1];
        String part3 = parts[2];

        if (!isThisDateWithin3MonthsRange(part3,"mm/dd/yyyy")){
            textViewDate.setText("Available");
        } else {
            textViewDate.setText("Donated Once! Not Available");
        }

      //  textViewPhone.setText(user.getBlood_city());

        return listViewItem;
    }

    public boolean isThisDateWithin3MonthsRange(String dateToValidate,
                                                String dateFromat) {

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);
        try {

            // if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);

            // current date after 3 months
            Calendar currentDateAfter3Months = Calendar.getInstance();
            currentDateAfter3Months.add(Calendar.MONTH, 3);

            // current date before 3 months
            Calendar currentDateBefore3Months = Calendar.getInstance();
            currentDateBefore3Months.add(Calendar.MONTH, -3);

           /* *//*
            System.out.println("\n\ncurrentDate : "
                    + Calendar.getInstance().getTime());
            System.out.println("currentDateAfter3Months : "
                    + currentDateAfter3Months.getTime());
            System.out.println("currentDateBefore3Months : "
                    + currentDateBefore3Months.getTime());
            System.out.println("dateToValidate : " + dateToValidate);
            */

            if (date.before(currentDateAfter3Months.getTime())
                    && date.after(currentDateBefore3Months.getTime())) {
                //ok everything is fine, date in range
                return true;

            } else {
                return false;
            }

        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }

    }
}