package com.mazizs.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mazizs.artspace.ui.theme.ArtSpaceTheme

//Fumgsi onCreate ini digunakan untuk mengedit tampilan aktivitas utama dengan menggunakan komponen UI yang telah didefinisikan dalam ArtSpaceApp
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme() {
                ArtSpaceApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ArtSpaceApp dimana merupakan komponen utama aplikasi dan umtuk mengatur tampilan utama aplikasi dan juga untuk mengatur Scaffold
@Composable
fun ArtSpaceApp() {
    //Mendeklarasikan variabel currentStep, currentStep merupakan bagian dari State management dalam compose
    //by remember digunakan untuk mengelola dan mempertahankan state dari variabel currentStep
    //mutableStateOf(1) digunakan untuk memulai currentStep dengan nilai awal 1 dan mengikuti perubahan nilai currentStep
    var currentStep by remember { mutableStateOf(1) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "ArtSpace",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize() //Untuk menyesuaikan tampilan diberbagai layar atau membuat tampilan menjadi responsif
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background
        ) {
            when (currentStep) {
                1 -> {
                    ArtSpaceTextAndImage(
                        drawableResourceId = R.drawable.monalisa,
                        contentDescriptionResourceId = R.string.monalisa,
                        titleResourceId = R.string.monalisa,
                        authorResourceId = R.string.Leonardo_da_Vinci,
                        onButtonNextClick = { currentStep = 2 },
                        onButtonPreviousClick = { currentStep = 4 })
                }
                2 -> {
                    ArtSpaceTextAndImage(
                        drawableResourceId = R.drawable.thestarrynight,
                        contentDescriptionResourceId = R.string.the_starry_night,
                        titleResourceId = R.string.the_starry_night,
                        authorResourceId = R.string.Vincent_van_Gogh,
                        onButtonNextClick = { currentStep = 3 },
                        onButtonPreviousClick = { currentStep = 1 })
                }
                3 -> {
                    ArtSpaceTextAndImage(
                        drawableResourceId = R.drawable.theoldguitarist,
                        contentDescriptionResourceId = R.string.the_old_guitarist,
                        titleResourceId = R.string.the_old_guitarist,
                        authorResourceId = R.string.Pablo_Picasso,
                        onButtonNextClick = { currentStep = 4 },
                        onButtonPreviousClick = { currentStep = 2 })
                }
                4 -> {
                    ArtSpaceTextAndImage(
                        drawableResourceId = R.drawable.theassofbalaamtalkingbeforeangel,
                        contentDescriptionResourceId = R.string.the_ass_of_balaam_talking_before_angel,
                        titleResourceId = R.string.the_ass_of_balaam_talking_before_angel,
                        authorResourceId = R.string.Rembrandt_van_Rijn,
                        onButtonNextClick = { currentStep = 1 },
                        onButtonPreviousClick = { currentStep = 3 })
                }
            }
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ArtSpaceTextAndImage yang dimana untuk menampilkan teks, gambar, dan tombol navigasi serta mengaturnya
@Composable
fun ArtSpaceTextAndImage(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    titleResourceId: Int,
    authorResourceId: Int,
    onButtonNextClick: () -> Unit,
    onButtonPreviousClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(25.dp)
    ) {
        Picture(
            drawableResourceId = drawableResourceId,
            contentDescriptionResourceId = contentDescriptionResourceId,
            modifier = Modifier
                .weight(1f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TitleAndAuthorText(
            titleResourceId = titleResourceId,
            authorResourceId = authorResourceId,
            modifier = Modifier
                .padding(20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        ButtonsNavigation(
            onButtonNextClick = onButtonNextClick,
            onButtonPreviousClick = onButtonPreviousClick,
            modifier = Modifier.fillMaxWidth() //Untuk menyesuaikan tombol previous dan next diberbagai layar atau mengubah tombol previous dan next menjadi responsif
        )
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi Picture yang dimana untuk mengatur tampilan gambar
@Composable
fun Picture(
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colorScheme.background,

        modifier = modifier
            .wrapContentHeight()
            .border(
                BorderStroke(2.dp, Color(105, 105, 105))
            )
    ) {
        Image(
            painter = painterResource(drawableResourceId),
            contentDescription = stringResource(contentDescriptionResourceId),
            modifier = modifier
                .padding(20.dp)
        )
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi TitleAndAuthorText untuk mengatur tampilan teks judul dan penulis karya seni dengan menggunakan warna background
@Composable
fun TitleAndAuthorText(
    modifier: Modifier = Modifier,
    titleResourceId: Int,
    authorResourceId: Int
) {
    Surface(
        color = Color.Yellow, //Untuk mengatur warna background teks judul dan penulis karya seni
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(titleResourceId),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.SansSerif
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(authorResourceId),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ButtonsNavigation mengatur tombol navigasi Previous dan Next
@Composable
fun ButtonsNavigation(
    modifier: Modifier = Modifier,
    onButtonNextClick: () -> Unit,
    onButtonPreviousClick: () -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentWidth(Alignment.CenterHorizontally),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = onButtonPreviousClick
        ) {
            Text(
                text = stringResource(id = R.string.button_previous),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            modifier = Modifier
                .padding(5.dp)
                .weight(1f),
            onClick = onButtonNextClick
        ) {
            Text(
                text = stringResource(id = R.string.button_next),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

//Fungsi komponen Composable dalam Jetpack Compose di bawah ini merupakan fungsi ArtSpacePreview yang digunakan untuk untuk menampilkan preview atau pratinjau dari ArtSpaceApp
@Preview
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme() {
        ArtSpaceApp()
    }
}