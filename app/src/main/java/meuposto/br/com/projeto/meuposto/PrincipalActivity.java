package meuposto.br.com.projeto.meuposto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import meuposto.br.com.projeto.meuposto.model.Combustivel;
import meuposto.br.com.projeto.meuposto.model.Posto;

import static android.content.ContentValues.TAG;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Posto posto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();


        posto = new Posto();
        List<Combustivel>combustivels = new ArrayList<Combustivel>();
        Combustivel combustivel = new Combustivel();
        combustivel.setTipo("Gasolina");
        combustivel.setPreco(3.90);
        Combustivel combustivel2 = new Combustivel();
        combustivel.setTipo("Gasolina aditivada");
        combustivel.setPreco(4.10);

        combustivels.add(combustivel);
        combustivels.add(combustivel2);

        posto.setCombustivel(combustivels);

        System.out.println("Tipo da gasolina: "+ combustivels.get(0).getTipo());
        System.out.println("Preço: "+ combustivels.get(0).getPreco());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        //ID criado no Actitivity principal Drawer
        if (id == R.id.nav_principal) {//Depois criar funcoes para fazer isso
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.add(R.id.containerId, new MapsFragment(), "MapsFragment");

            transaction.commitAllowingStateLoss();
        }

        if (id == R.id.novoPostoId) {


    Intent intent = new Intent(this, CadastroPostoActivity.class);
    startActivity(intent);


        }

        if (id == R.id.listaPostosId) {


    Intent intent = new Intent(this, ListaPostosActivity.class);
    startActivity(intent);
    System.out.println("Clicou!!!!!!!");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
