package win.gdzrch.kotlinsample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import win.gdzrch.kotlinsample.network.HttpManager
import win.gdzrch.kotlinsample.network.Repo
import win.gdzrch.kotlinsample.network.SearchRepo

class ScrollingActivity : AppCompatActivity() {

//    @BindView(R.id.fab)
//    lateinit var fab :FloatingActionButton
//
//    @BindView(R.id.toolbar)
//    lateinit var toolbar :Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyActivityUI().setContentView(this)
//        setContentView(R.layout.activity_scrolling)
//        ButterKnife.bind(this)
//        setSupportActionBar(toolbar)
//
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Cancle", {view ->
//                        view.visibility = View.GONE
//                    }).show()
//        }
//        text.setOnClickListener{_ ->
//            toast("app_bar")
//            Toast.makeText(this,"app_bar",Toast.LENGTH_SHORT).show()
//        }
//        val list:List<Int> = listOf()
//        println(list?.size ?: 8)

        val observable = HttpManager.service.user()
        val observableList = HttpManager.service.listRepo()
        val observableSearch = HttpManager.service.search("kotlin android")
        val subject = object  : Observer<Repo> {
            override fun onError(p0: Throwable?) {
                Logger.i("onError"+p0)
            }

            override fun onComplete() {
                Logger.i("onComplete")
            }

            override fun onNext(repo: Repo?) {
                Logger.i("onNext"+repo)
            }

            override fun onSubscribe(p0: Disposable?) {
                Logger.i("onSubscribe")
            }

        }

        val subjectList = object  : Observer<List<Repo>> {
            override fun onError(p0: Throwable?) {
                Logger.i("onError"+p0)
            }

            override fun onComplete() {
                Logger.i("onComplete")
            }

            override fun onNext(repo: List<Repo>?) {
                Logger.i("onNext"+repo)
            }

            override fun onSubscribe(p0: Disposable?) {
                Logger.i("onSubscribe")
            }

        }
        val subjectSearch = object  : Observer<SearchRepo> {
            override fun onError(p0: Throwable?) {
                Logger.i("onError"+p0)
            }

            override fun onComplete() {
                Logger.i("onComplete")
            }

            override fun onNext(repo: SearchRepo?) {
                Logger.i("onNext"+repo)
                val adapter :ListAdapter = ListAdapter(ctx , R.layout.list_item , (1..40).map { it.toString() })
            }

            override fun onSubscribe(p0: Disposable?) {
                Logger.i("onSubscribe")
            }

        }

        observableList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subjectList)

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subject)

        observableSearch
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subjectSearch)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}


class MyActivityUI : AnkoComponent<ScrollingActivity> {


    override fun createView(ui: AnkoContext<ScrollingActivity>) = ui.apply {
        //val adapter :ListAdapter = ListAdapter(ctx , R.layout.list_item , (1..40).map { it.toString() })

        verticalLayout {
            val list = listView {
                onItemClick { _,_,_,_ -> ctx.toast("item")  }
            }

            //list.adapter = adapter
        }
    }.view
}
