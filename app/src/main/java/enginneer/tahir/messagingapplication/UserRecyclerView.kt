package enginneer.tahir.messagingapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

class UserRecyclerView( val context: Context,val userList:ArrayList<User>):RecyclerView.Adapter<UserRecyclerView.ViewHolder>() {


    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
     val  view : View = LayoutInflater.from(context).inflate(R.layout.user_layout,parent,false)
        return  ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val  currentUser = userList[position]
        holder.nameTxt.text = currentUser.name

        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
    return userList.size
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val nameTxt = itemView.findViewById<TextView>(R.id.txt_name)
    }

}