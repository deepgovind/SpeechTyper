package speechtyper;
 
//import static DSpeechTyper.DSpeechTyper.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

public class SpeechTyper extends javax.swing.JFrame {
 
    String HindiWords[] = {"a", "Aadambara", "Aadamshumari", "Aadhaara", "Aadhyaatmika", "Aadikaala", "Aafata", "Aahuti", "Aahvaana", "Aakaasha", "Aakarshanmaya", "Aakarshita", "Aakhira", "Aalasya", "Aapasa", "Aaraamdaayaka", "Aasakti", "Aasandhari", "Aasha", "Aashirvada", "Aasmaana", "Aastika", "Aasuree", "Aata", "Aathata", "Aatmghaata", "Aavaagamana", "Aayushvaana", "Adheeryata", "Adhikaara", "Adhikaari", "Agnee", "Agyankaala", "Ahilyayaina", "Ahinsaka", "Ajaba", "Ajameela", "Akarma", "Alankaara", "Alapkaala", "Amaanata", "othersa", "Amana", "Amarkathaa", "Amarnatha", "Ameerchanda", "Amma", "Amoolya", "Anaadi", "Anaasakta", "Andaa", "Andaaza", "Andhashraddha", "Andhkaara", "Angustha", "Anjaana", "Anpadha", "Antara", "Antarmukhi", "Antarmukhta", "Antaryaami", "Anubhuti", "Anusaara", "Anushaasana", "Apaara", "Apavitrata", "Apkaara", "Apkaara", "Apnana", "Apraapti", "Apramapaara", "sake", "Asamartha", "Asambhava", "Ashta", "Athaha", "Athva", "Atindriya", "Avagya", "Avashya", "Avasthaa", "Avinaashi", "Avtaara", "Avyabhichaari", "Ba", "Baajoli", "Baana", "Baandhnaa", "Baarooda", "Bachdevaala", "Badala", "Badboo", "Badnaama", "Badrinatha", "Badtara", "Bahaadura", "Bahaaneybaaza", "Bahlaana", "Bahoorupi", "Bahukaala", "Baiscope", "Bala", "Balihaara", "Bandara", "Bandgi", "Bandhaaymaana", "Bandhanmukta", "Bandooka", "Barana", "Barapana", "Bartuna", "Batti", "Beejroopa", "Begampura", "Behada", "Behosha", "Bera", "Beshumara", "Beyhaala", "Bhaageeratha", "Bhaagna", "Bhaagya", "Bhaagyaheena", "Bhaagyashali", "Bhaana", "Bhaasna", "Bhaava", "Bhaavi", "Bhaavna", "devotiona", "Bhagata", "Bhageedaara", "Bhagvanuvaacha", "Bhaiinsa", "Bhainta", "Bhaita", "Bhala", "Bhambhora", "Bhandaara", "Bhandaari", "Bharpoora", "Bhasmaasoora", "himselfa", "Bhatthi", "Bhautika", "Bhavishya", "Bhaybheeta", "Bheekha", "Bheetara", "Bhikhaari", "Bhiksha", "Bhittara", "Bhola", "Bholanaatha", "Bhookampa", "Bhoola", "Bhramri", "Bhrukuti", "Bichhana", "Bichhoo", "Bigara", "Bigarna", "Bina", "Bindoo", "Bojha", "Boota", "Brahaspati", "Brahmachari", "Brahmaloka", "Brahmcharya", "Budbuda", "Ca", "Chaalaaki", "Chaandi", "Chahe", "Chaitanya", "Chakrawarti", "Chalana", "Chamaata", "Chamaka", "Chamatkaara", "Chamba", "Champa", "Chamri", "Chanchala", "Chandaala", "Chandana", "Chandravanshi", "Charana", "Chardna", "garmenta", "Chaupri", "Chauraha", "Chehra", "Chela", "Cheytavani", "Chhama", "Chhatra", "Chhatrachhaya", "Chhee", "Chheenna", "Chhipaana", "Chhutkaara", "Chidchidapana", "Chinta", "Chintana", "Chirdhaana", "Chita", "Chobchini", "Chola", "Chora", "Chumbaka", "Chusta", "Da", "Daaga", "Daala", "Daari", "Daasi", "Daata", "Dabbi", "Dadhichi", "bonesa", "Daheja", "Dalaala", "Danda", "Danda", "Dara", "Darbaara", "Dardnaaka", "Darkaara", "Darpana", "Dasa", "Dasna", "Davaai", "Deemaka", "Deha", "Deha", "Dehbhaana", "Dehdhaari", "Dehi", "Desha", "Deyvaala", "Dhaaga", "Dhaarna", "Dhamchakra", "Dhandha", "Dhandhadhori", "Dharma", "Dharmraaja", "Dheela", "Dhera", "Dhindhora", "Dhoola", "Dhoomdhaama", "Dhuaana", "Dikkaata", "Dila", "Dilshikasta", "Diltaktha", "Divasa", "Dolayamaana", "Doobana", "Dozaka", "Drishtanta", "Drishti", "Drishya", "Drudhta", "Drushta", "Duaa", "Dukha", "Dukha", "Dukhdhaama", "Dunyavi", "Durbhagyashali", "Durgati", "Durlabha", "Dushmana", "conflicta", "Dwaaparyuga", "Ea", "Eenta", "Ekaanta", "Ekagra", "Ekagrachita", "Ekta", "Evaja", "Fa", "Fakeera", "Fakeerchanda", "Fakhura", "Falaana", "Faldayaka", "Faltoo", "Faraakdila", "Faraka", "Farishta", "Farza", "Farza", "Fasna", "Fazeelata", "Feykna", "Fhulvardi", "Fida", "Fikraata", "Footna", "Furna", "Ga", "Gaali", "Gaantha", "Gaayaba", "Gaayana", "Gaayana", "Gada", "Gaflata", "Gahana", "Galna", "Galti", "Gama", "Ganna", "Ganvaana", "Gaona", "Gapora", "Garbara", "Garbha", "Gareeba", "Gati", "Gatmata", "considereda", "Geelapana", "anxiousa", "Ghanteya", "aadi", "Bellsa", "gharghaata", "Ghora", "Ghotala", "Ghottna", "Ghuddaura", "Ghudsavara", "Ghunghata", "Girna", "Glani", "Goada", "Goonjna", "Gota", "Goya", "Graahaka", "Grahastha", "Grahasthi", "Gruta", "Guddiya", "Gufa", "Guhya", "Gulaama", "Guldasta", "Gulgula", "Gulshana", "Guma", "Gumbheera", "Gumbheerta", "Gungaana", "gungrahi", "Gupta", "Gussa", "Gutka", "Ha", "Haalata", "Haazira", "Haazira", "Haazira", "Habacha", "Hada", "Haddi", "Hadgooda", "Hahakaara", "Hakdaara", "Halchala", "Halwa", "Hangama", "Hansa", "Hareka", "Harjaa", "Harna", "Harsha", "Harshitmukha", "Hasi", "Hasna", "Hatheyli", "Hathiyaara", "Havalaya", "Hazama", "Heera", "Heereya", "Hilaana", "Hinsa", "Hinsaka", "Hisaaba", "Hubahoo", "Hujata", "Hullaasa", "Huma", "Ia", "Ikattha", "Ikattha", "Ilahi", "Ilzaama", "Imaandaara", "Indra", "Indrasabhaa", "Insaafa", "Irshaa", "Isa", "Itihaasa", "Itra", "Izzata", "Ja", "Jaadoo", "Jaadoogara", "Jaageera", "Jaala", "Jaamra", "Jaanna", "Jaayedaada", "Jachnaa", "Jagaana", "Jahaana", "Jahana", "Jahannuma", "Jala", "Jalaana", "Jamdoota", "Janama", "adheekaara", "Janampatri", "Janma", "Jansankhya", "Japa", "Japna", "Jarda", "Jarda", "Javaahraata", "Jawhari", "Jaya", "Jayanti", "Jeeva", "Jeevanbandha", "Jeevanmukti", "Jeevatma", "Jhaara", "Jhagarna", "Jhagda", "Jhanjhata", "Jharmui", "Jhata", "Jholi", "Jhoolaa", "Jhutka", "Jidhara", "Jisama", "Jismaani", "Jiyadaana", "Johara", "Jokhama", "Jootti", "Juaa", "Junka", "Ka", "Kaaga", "Kaaga", "Kaajala", "Kaala", "Kaalaapana", "Kaalona", "Kaantaa", "Kaapari", "Kaarana", "Kaarigara", "Kaaroona", "khazaana", "Kaatha", "Kaayda", "Kaaydesira", "Kabradaakhila", "Kabrastaana", "Kachhua", "Kadara", "Kahaavata", "Kalaa", "Kalaabaazi", "Kalasha", "Kaliyuga", "Kalpa", "Kalpana", "Kalyaana", "Kamaai", "Kamala", "Kamalpushpa", "Kambakhta", "Kamzora", "Kanchana", "Kandhaa", "Kangana", "Kanjoosa", "Kankada", "Kantha", "Kantha", "Kapaata", "Kapoota", "Karaamata", "Karankaravanhaara", "througha", "Karmateeta", "karma", "Karmindriyaana", "Karnighora", "thingsa", "Karora", "Karorpati", "Karva", "Kashisha", "Kashta", "Kata", "Kataari", "Katha", "Kathinai", "Kauri", "Kauri", "Kautuka", "Kayamata", "Kechehri", "Khaami", "Khaanpaana", "Khaasa", "Khaatri", "Khalaasa", "Kharcha", "Khatarnaaka", "Khatiya", "Khatra", "Khaufnaaka", "Khazaana", "Khinchna", "Khitkhita", "Khivaiya", "Kholna", "Khoona", "Khuda", "Khudaikhidmatgaara", "Godlya", "Khumari", "Khunenahaka", "Khuraka", "Khushboodaara", "Khushnaseeba", "Khutna", "Kinaara", "Kiraaydaara", "Kirana", "Kisama", "Kriti", "Kriyakarma", "Krodha", "Kshama", "Kshatriya", "Kshira", "Kshira", "Kshirkhanda", "Kuaa", "Kubjayeina", "Kudrata", "Kudrati", "Kukarma", "Kula", "Kulkalankita", "Kumati", "Kumbhakarana", "Kunvari", "Kushti", "Kutiya", "Kutumba", "La", "Laadli", "Laaja", "Laalsaa", "Laata", "Laathi", "Laayaka", "Labaadi", "Lagaava", "Lage", "Lahara", "Laisuna", "Lajja", "Lakeera", "Lakhpati", "Lalkaara", "Langara", "Langdaana", "Latakna", "Leena", "Leyna", "Lobha", "Loga", "Loha", "Loka", "Loona", "Lovleena", "Ma", "Maafi", "Maafika", "Maali", "Maana", "Maana", "Maananiya", "Maanava", "Maanjhi", "Maanyata", "Maaraamaari", "Maashooka", "Maatele", "Maatha", "Maatha", "Maatha", "Maatra", "Madada", "Vishnu", "Madhurta", "Madogari", "Magroora", "Mahaana", "Mahaashatru", "Mahaavaakya", "Mahaaveera", "Mahadaani", "Mahadeva", "Mahakaala", "Mahala", "Mahatma", "Mahatva", "Mahima", "Majboori", "Mamatva", "Mamta", "Mana", "Mana", "Mandira", "Mandodari", "Manka", "Manmanabhava", "Manmata", "Manoranjana", "Mansa", "Manthana", "Marjeeva", "Marna", "Martaba", "Maryaada", "Mashhoora", "Masta", "Mastaka", "Mastaka", "Mata", "Mauja", "Mauka", "Mauta", "Mazboota", "Meetha", "Meina", "Mera", "Meroo", "Milana", "Misaala", "Mithaasa", "Miya", "Moha", "Mohabbata", "Moksha", "Moola", "Moolvatana", "Moolya", "Moolyavaana", "Moonjhna", "Moorakha", "Moosaldhaara", "barsaata", "Moti", "Mrityuloka", "Mukhvanshaavali", "Mukhya", "Murbi", "Murda", "Murdaabaada", "Murjhaana", "Musaaphira", "Musafiri", "Mushkila", "Na", "Naachna", "Naafarmaanberdaara", "Naahaka", "Naala", "Naama", "Naama", "Naama", "Naari", "Naastika", "Naataka", "Naava", "Nabza", "Nadi", "Nafrata", "Nagaara", "Nahara", "Naina", "Naiya", "Namaaza", "Namana", "Namrata", "Nangana", "Nangana", "Nara", "Nasa", "Naseeba", "Naseebdaara", "Nasha", "attachmenta", "Naveenta", "Navnirmarna", "Navyuga", "Naymeenaatha", "Neelama", "Neshta", "Netra", "Nichai", "Nidhanke", "Nihaala", "Nihaala", "Niji", "Nimantrana", "Nimita", "Nimita", "Ninda", "Nindra", "Niraala", "Nirahankaari", "Nirakaari", "Nirantara", "Nirbala", "Nirbandhana", "Nirdhana", "Nirguna", "Nirmaana", "Nirmaanchita", "Nirnaya", "Nirogi", "Nirsankalpa", "Nirvighana", "Nirvikaari", "Nischita", "Nishchaya", "Niswaartha", "Nivaarana", "Nivruti", "Niyama", "Noondhna", "Nyochhaavara", "Pa", "Paadera", "Paadri", "Paapa", "Paapa", "Paapi", "Paara", "Paarasa", "Paartdhari", "Paataala", "Paatra", "Pada", "Padaartha", "Padhaai", "Padhraamani", "Padmapadama", "Padmapadama", "Pahaarda", "Pai", "Paiyta", "Panda", "Pankha", "Parama", "Paramatma", "Paramdhama", "Parameshwara", "Parampara", "Parampita", "Parampriya", "Parcha", "Parchhaaya", "Pardaa", "Pardarshana", "Pareshaana", "Parheza", "Paricheya", "Parikrama", "Parinaama", "Parivaara", "Parivartana", "Parloka", "Parmaartha", "Paropkaari", "Parpancha", "Parva", "Parvaana", "Pashchaataapa", "Patana", "Pati", "Patita", "Patraani", "Patthara", "Payda", "Paydaaisha", "Paygaama", "Paygambura", "Paysha", "Peerdi", "Peesna", "Peetna", "Pehanna", "Pehra", "Phoola", "Pichhardi", "Pighalna", "Pinda", "Pinjra", "Pita", "Pitra", "Pooja", "Pooncha", "Posha", "Potamela", "Potreya", "Potriyaana", "beinga", "Praarthna", "Prabhaava", "Prabhaavshaali", "Prabhu", "Prachaara", "Pracheena", "Pradarshani", "Pragati", "Prairaka", "Praja", "Prajapita", "Prajvalita", "Prakaasha", "brightera", "Prakhyaata", "Prakrutika", "Pralya", "Prasannchita", "Prashanchita", "Prashansa", "Prashna", "Prathama", "Pratimaa", "Pratishata", "Pratyakshfala", "Pratyakshta", "Prayalope", "Prayatana", "Prayoga", "Prayogshaala", "Preeta", "Prema", "Prerna", "Prithvi", "Pujari", "Pujya", "Pukhraajpari", "Punarjanama", "Punya", "Puraana", "Purusharatha", "Purushartha", "Purza", "Putla", "Putravaana", "Pyaara", "Pyada", "Ra", "Raaga", "Raai", "Raaja", "Raajneeti", "Raasa", "Raashi", "Raaza", "Raazyukta", "Rachna", "Rachta", "Radiyaana", "Raftaara", "Rahema", "Rahita", "Rahu", "Rai", "Raiteeli", "Rajasva", "yagya", "sacrificeda", "Raksha", "Rakshaka", "Rakta", "Ramata", "Ramneeka", "Ramta", "Rasa", "Rasama", "Ratana", "Raunaka", "Raurava", "Regzina", "Rehvaasi", "Riddhi", "Ridha", "Rinchaka", "Rishta", "Robe", "Romaancha", "Rona", "Roopdhari", "Rooprekha", "Roothna", "Roshni", "Ruchi", "Rudra", "Ruhaani", "Ruhaba", "Ruhaneeyata", "Ruhrihaana", "Rui", "Rukna", "Runka", "Rustama", "Sa", "Saadhaarana", "Saadhana", "Saafa", "Saagara", "Saahasa", "Saahibzaada", "Saahukaara", "Saakaari", "Saakshaatkaara", "Saakshee", "Saaligraama", "Saamna", "Saara", "Saavdhaani", "Saaza", "Sabaka", "Sabha", "Saboota", "Saccha", "Sadgati", "Safalta", "Safara", "Sagaai", "Sagaya", "Sahaara", "Sahaayta", "Sahana", "Sahansheelta", "Saheja", "Sahita", "Sahyoga", "Sahyogi", "Sajaana", "Sajnee", "Sakaasha", "Sakha", "Salaami", "Samaadhaana", "Samaaroha", "Samaarpita", "Samajhdaara", "Samajhna", "Samasya", "Samaya", "Sambandha", "Sambandhi", "Sambhava", "Sameepa", "Sametne", "Sampativana", "Sampradaaya", "Samraata", "Sanaatana", "Sandeshi", "Sangdosha", "Sanghaara", "Sanharni", "Sanjivani", "Sankalpa", "Sankocha", "Sannaataa", "Sanshaya", "Sanskaara", "Sanstha", "Santulana", "Santushta", "Santushtta", "Sanyaasa", "Sarna", "Sarpa", "Sarvavyapi", "Sarvodaya", "alla", "Sasura", "Satsanga", "Sattka", "Satya", "katha","Satyaanaasha", "Satyama", "sundarama",  "Truth,a", "Satyata", "Satyavadi", "Satyuga", "Saubhagya", "Saugaata", "Sauteyla", "Savaara", "Savaari", "Sayaanaa", "Sazaa", "Seka", "Sena", "Seyhata", "Shaama", "Shaantchita", "Shahzaadi", "Shaitaana", "Shaka", "Shama", "Shamshaana", "Shani", "Shanka", "Shankha", "Shanti", "Sharana", "Shareera", "Sharma", "Shatruta", "Shera", "Shikaayata", "Shiromani", "Shobhvaana", "Shokvaateeka", "Shraapa", "Shreshthachaari", "Shrimata", "Shiva", "Shringaara", "Shubha", "Shubha", "Shubha", "Shudra", "Shuru", "Shyaama", "Siddha", "Siddhaanta", "Siira", "Sikeeladhe", "Silaee", "Simrana", "Sindhoo", "Sinhaasana", "Sneha", "Sojhara", "Somrasa", "Sookshma", "Soolee", "Spashta", "Srishti", "Sthaapaka", "Sthaapana", "Sthaapana", "Sthai", "Sthira", "Stuti", "Sudha", "Sugandha", "Sujaaga", "Sukarma", "Sukha", "Sukha", "Sukhdhaama", "Sukhmaya", "Sumati", "Sundara", "Sunga", "Sunvaai", "Suputra", "Surjeeta", "Surmandala", "Suryavanshi", "Susta", "Susti", "Swaada", "Swaaha", "Swaartha", "Swaasa", "Swabhaava", "Swabhavika", "Swachhata", "Swachintaka", "eternala", "Swadarshanchakra", "Swadesha", "Swamaana", "Swapana", "Swarga", "Swayama", "Sweekaara", "Swyamvara", "princessa", "Ta", "Taajdhaaree", "Taalaa", "Taalaaba", "Taali", "Taamba", "Taareekha", "Tabiyata", "Takdeervaana", "Takiya", "Talvaara", "Tandrusta", "Tanga", "Tanta", "Tapasya", "Tapata", "Tapna", "Tarafa", "Tarafti", "Tareeka", "Tatatvama", "Tatpara", "Tatva", "Tausee", "Teejree", "Teeli", "Teera", "Teertha", "Teevragati", "Tejomaya", "Teyluka", "Teyrna", "Thakaavata", "Thakna", "Thappada", "Thhagi", "livinga", "Thikkara", "Thoka", "Thokara", "Thuga", "Thukkraana", "Thura", "Tikaoo", "Tiraskaara", "Tivaata", "Tokri", "Toota", "Torna", "Tretayuga", "Trikaaldarshi", "time", "Triloka", "Trilokinatha", "Trinetri", "Trupta", "Tukra", "Tvameva", "Tyauhara", "Ua", "Ubasi", "Udaarta", "Udaya", "Uddeshya", "Uddhaara", "Uddhaara", "Udhaara", "Udhghatana", "Ulhana", "Uljhana", "Ullanghana", "Ullhaasa", "Ulloo", "Umanga", "Umeedvaara", "Unnati", "Upadrava", "Upasthita", "Updesha", "Uphaara", "Urdna", "Utaara", "Utaavlaa", "Utarti", "Utpanna", "Utsaaha", "Utsava", "Utthaana", "Va", "Va", "Vaacha", "Vaanprastha", "Vaanprasthi", "Vaara", "Vaari", "Vaarisa", "Vaasna", "Vaataavarana", "Vaayu", "Vaayumandala", "Vafaadaara", "Vaikuntha", "Vaishya", "Vaishyaalaya", "Vaitarni", "Vakeela", "Vakta", "Vallabhaachari", "Vande", "Vani", "Vansha", "Vanshi", "Vara", "Vardaani", "Vardata", "Varnaa", "Varnana", "Varsa", "Vasha", "Vasheebhoota", "Vasheekarana", "mantra", "Vatana", "Vayaapaara", "Vazana", "Veda", "Vidai", "Videhi", "Videshi", "Vidhaana", "Vidhaata", "Vidhi", "Vighana", "Vijaya", "Vikarma", "Vikraala", "Vilaayata", "Vinaasha", "Vinaashi", "Vinashaynti", "Vishesha", "Visheshta", "Visheya", "nadi",  "Vishraama", "Vishwa", "Vishwa", "Vishwa", "Vishwaasa", "Vismruti", "Vistaara", "Viveka", "Viyogi", "Vraksharopana", "Vrakshpati", "Vruddhi", "Vrundavana", "Vruti", "Vyaaja", "Vyabhichari", "Vyakta", "Vyartha", "Vyateeta", "Vyavahaara", "Ya", "Yaadgara", "Yaani", "Yadayadaahi", "Yagya", "Yamdoota", "Yantra", "Yathartha", "Yathashakti", "Yogyata", "Yogyukta", "Yoniyaana", "Yuddha", "Yugala", "Yuktiyukta", "Yuva", "Za", "Zahara", "Zamina", "Zanzeera", "Zara", "Zeyvera", "Zidda", "Zimmevaari", "Zinda", "Zindabada"};
   static String EngW[] = {"type","this"};
   static String EngWH[] = {"t:aayapa","disa"};
    
    
    static boolean  isStart ;
     static String KeyString ="";
     static int curcer = -1;
    static int cc;
           static  String Matra[] = {"n:","aa","ei","ii","oo","o-","au","ai","uu","i","a","u"};
           static  String MatraH[] = {"ं","1ा" ,"1े" ,"1ी" ,"1ो","1ो" ,"1ौ" ,"1ै" ,"1ू" ,"1ि","1","1ु"};
              
           static   String MatraHL[] = {"1ा" ,"1े","1ी" ,"1ो","1ौ" ,"1ै","1ू" ,"1ि","1ु","न्:","1"};
           static   String MatraL[] = {"आ","ए","ई","ओ","औ","ऐ","ऊ","इ","उ","ं","अ"};
            
           static  String HiHa[]  = {"jnj~","kshh","nd~","dh~", "kh","k","gh","n","chh","ch","jh","j","th","t:","t","d~","d:",
                              "dh","d","ph","p", "bh","b","m","y","r","l","v","shh","sh","s","h","g~","g"};
           static  String HiHaH[] = {"ज्ञ्","क्ष्","ण्","ढ्","ख्","क्","घ्","न्","छ्","च्","झ्","ज्","थ्","ट्", "त्","ङ्","ड्",
                               "ध्","द्","फ्","प्","भ्","ब्","म्","य्","र्","ल्","व्","ष्","श्","स्","ह्","ग्","ग्"};
   ////////////////////////      
            
              static  String MatraT[] = {"n:","aa","ee","e", "au","ai","oo","o","i","a","u"};
           static  String MatraHT[] = {"ं","1ा" ,"1ी" ,"1े" ,"1ौ" ,"1ै" ,"1ू" ,"1ो","1ि","1","1ु"};
              
           static   String MatraHLT[] = {"1ा" ,"1े","1ी" ,"1ो","1ौ" ,"1ै","1ू" ,"1ि","1ु","न्:","1"};
           static   String MatraLT[] = {"आ","ए","ई","ओ","औ","ऐ","ऊ","इ","उ","ं","अ"};
            
           static  String HiHaT[]  = {"gy","ksh","N","dh", "kh","k","gh","n~","n","chh","ch","jh","j","th","t:","t",
                              "dh","d","ph","p", "bh","b","m","y","r","l","v","Sh","sh","s","h","g","f","z"};
           static  String HiHaHT[] = {"ज्ञ्","क्ष्","ण्","ढ्","ख्","क्","घ्","ङ्","न्","छ्","च्","झ्","ज्","थ्","ट्", "त्",
                               "ध्","द्","फ्","प्","भ्","ब्","म्","य्","र्","ल्","व्","ष्","श्","स्","ह्","ग्","फ्","ज्"};
       
           
     //char HinhiWords[] = {'k','h'};
     //char EngWords[] = {""};
      static String er;
     public SpeechTyper() {
          initComponents();
     }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jDialog3 = new javax.swing.JDialog();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jDialog4 = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jPopupMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPopupMenu1MouseClicked(evt);
            }
        });

        jMenuItem2.setText("Copy All");
        jMenuItem2.setToolTipText("");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Copy Selected");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        jMenuItem4.setText("Cut All");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem4);

        jMenuItem6.setText("About");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem6);

        jMenuItem1.setText("Help");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jDialog1.setResizable(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 141, 166));
        jLabel2.setText("   HindiSpeehTyper");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setText("Mobile No        :           7388966141");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setText("Product Version :          1.0.1");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setText("Product Name   :           SpeechTyper");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setText("CopyRight        :           © Deep govind 2016 ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setText("EMail  Id          :           deepgovind4@gmail.com");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setText("Education From:      Govt. Polytechnic unnao");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Only 2 month only ");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 9, Short.MAX_VALUE))
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDialog2.setResizable(false);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText(" Hindi Speech Typer -------\n   \n   1) open Software \n   2) if any Problam to Open or Recognize or \n       Program not work \n       then\n        open SpeechType.bat or Open software by \n        by run command java -jar SpeechTyper.jar\n        and see the Problam in command .\n   3) Click Start button to Start recognizing \n     and \n     Click stop to stop any time recognization\n   4) If you need a key Board then Open using \n     click Show Keyboard\n   5) To copy Hindi Text , Right click on Text \n      area You can use \n      to copy  (Clt + c) , \n      to past  (Clt + v) ,\n      to cut   (Clt + x) ,\n\n  Informetion : \n     Use Head Phone or Smart phone Mic for Batter words\n     coeection ");
        jTextArea2.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
        );

        jTextField2.setText("jTextField2");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList2);

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        jDialog4.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                jDialog4WindowClosing(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Youre Software Expired................. ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Expired - By : Deep govind ");

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hindi Speech Typer By - Deep govind");
        setBackground(new java.awt.Color(102, 255, 0));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(185, 197, 209), 30));
        jTextArea1.setCaretColor(new java.awt.Color(84, 240, 6));
        jTextArea1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextArea1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextArea1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextArea1MouseReleased(evt);
            }
        });
        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextArea1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextArea1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Start");
        jButton2.setActionCommand("StartBtn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Stop");
        jButton1.setActionCommand("StopBtn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 204));
        jLabel1.setText("Off");

        jButton3.setText("|");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton74.setText(",");
        jButton74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton74ActionPerformed(evt);
            }
        });

        jButton4.setText("”");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("“");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton74)
                .addGap(12, 12, 12)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel1))
        );

        jLabel1.getAccessibleContext().setAccessibleName("Suitch");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     private static void InitHiTy() 
      { 
        er =  DSpeechTyper.DSpeechTyper.initHi();
          
          
      }
    
     private static String EntoHiKey(String str)
     {
         String s = str;
           
           for(int fc = 0;fc<HiHaT.length;fc++)
              {
                  s  =  s.replace(HiHaT[fc],HiHaHT[fc]);
              }
              
             for(int ma = 0;ma<MatraT.length;ma++)
             {
                  s  =  s.replace(MatraT[ma],MatraHT[ma]);
             }
                s  =  s.replace("्1", ""); 
             
              for(int ma = 0;ma<MatraLT.length;ma++)
             {
                  s  =  s.replace(MatraHLT[ma],MatraLT[ma]);
            }
              
         return s;
     }
    private static String EntoHi(String str)
    { 
             String s =  str;
               
                 // "ञ्","ठ्","", nd~/"",""," "् //////g~/bj~/j~/k~
              for(int fc = 0;fc<HiHa.length;fc++)
              {
                  s  =  s.replace(HiHa[fc],HiHaH[fc]);
              }
              
             for(int ma = 0;ma<Matra.length;ma++)
             {
                  s  =  s.replace(Matra[ma],MatraH[ma]);
             }
                s  =  s.replace("्1", ""); 
             
              for(int ma = 0;ma<MatraL.length;ma++)
             {
                  s  =  s.replace(MatraHL[ma],MatraL[ma]);
            }
           
             // System.out.println(" "+s);
         
         return s; 
     }
      class RecognizeThread extends Thread {

        public RecognizeThread() {
             super("RecognizeThread");
         }

              public void run()
              {
                     while (isStart) 
		  {
                         try
			{
				Thread.sleep(200);
                        }
			catch (InterruptedException e){}
                        while (DSpeechTyper.DSpeechTyper.getRecognizerQueueSize() > 0)
			{
			   String s = DSpeechTyper.DSpeechTyper.popRecognizedString();
                       
                                        // System.out.println("You said: " + s);
                                jTextArea1.insert(EntoHi(s) + " ", jTextArea1.getCaretPosition());
                                
                                jTextArea1.setCaretPosition(jTextArea1.getText().length());
                        }
                  }
                 
                
              jLabel1.setText("Off");
              jLabel1.setForeground(new java.awt.Color(255, 0, 0));
             jButton2.setEnabled(true);
             jButton1.setEnabled(false);
            }
        }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          
         
           isStart = true;
           jButton1.setEnabled(true);
           jButton2.setEnabled(false);
                  
               DSpeechTyper.DSpeechTyper.setRecognizerEnabled(true);
               jLabel1.setText("Speak in Hindi...");
               jLabel1.setForeground(new java.awt.Color(0, 0, 255));
             (new RecognizeThread()).start();
             
    }//GEN-LAST:event_jButton2ActionPerformed
   
        class InitThread extends Thread {

          public InitThread() {
            super("Init");
            }
              public void run() {
              javax.swing.JWindow lw = new javax.swing.JWindow();
               javax.swing.JLabel jb= new javax.swing.JLabel(new javax.swing.ImageIcon("SpeechTyper.jpg"));
        
               lw.getContentPane().add(jb, BorderLayout.CENTER);
               lw.setSize(554,200);
               lw.setVisible(true);
               lw.setLocationRelativeTo(null);
              // lw.setLocation(200, 200);
               
              InitHiTy();
             
               lw.setVisible(false);
               setVisible(true);
               jLabel1.setText("Library Complet Click Start ");
              jButton2.setEnabled(true);
             jTextArea1.append(er);
            }
        }
      private  void SetText(JTextField Tf,String str) {
         Tf.setText(str);
    }

    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }
       public static void setupAutoComplete(final javax.swing.JTextArea txtInput, final ArrayList<String> items) {
         final DefaultComboBoxModel model = new DefaultComboBoxModel();
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInput, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                       // txtInput.setText(cbInput.getSelectedItem().toString());
                          txtInput.replaceRange(cbInput.getSelectedItem().toString(), curcer, cc);
                        cbInput.setPopupVisible(false);
                        KeyString=  "";
                        curcer =-1;
                         
                    }
                }
            }
 
        });

        txtInput.addKeyListener(new KeyAdapter() {
    
            @Override
            public void keyPressed(java.awt.event.KeyEvent e)
            {
              try
              {
                   
              
                if(KeyString.length() >0 )
               {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(java.awt.event.KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER || e.getKeyCode() == java.awt.event.KeyEvent.VK_UP || e.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) 
                    {
                        String str1 = cbInput.getSelectedItem().toString();
                        if(str1.lastIndexOf("्") == str1.length()-1)
                        {
                          str1 =   str1.substring(0, str1.length()-1);
                        }
                       // txtInput.setText(cbInput.getSelectedItem().toString());
                      //  txtInput.insert(cbInput.getSelectedItem().toString(), curcer);
                        txtInput.replaceRange(str1, curcer, cc);
                        cbInput.setPopupVisible(false);
                        KeyString=  "";
                        curcer =-1;
                         
                    }
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
              }
               }
               catch (Exception ex) 
               {
                  java.util.logging.Logger.getLogger(SpeechTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
              }  
           }
               
                
        });
        txtInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
               // String input = txtInput.getText();
               
               
                
                String input =  KeyString.toString();
                if (!input.isEmpty()) 
                {   
                    // model.addElement(EntoHiKey(KeyString.concat("a")));
                    model.addElement(EntoHiKey(input));
                    for(int j = 0;j<EngW.length;j++)
                    {
                        if(input.contains(EngW[j]))
                        {
                            model.addElement(EntoHiKey(EngWH[j]));
                        }
                    }
                    
                    
                    model.addElement(input);
                    
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                           // model.addElement(item);
                             model.addElement(EntoHiKey(item.toLowerCase()));
                        }
                    }
                }
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }

             
        });
        txtInput.setLayout(new BorderLayout());
        txtInput.add(cbInput, BorderLayout.SOUTH);
     }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
         
           
           Calendar ed = Calendar.getInstance();
           ed.set(2016, 10, 1);
           if(false/*Calendar.getInstance().after(ed)*/)
           {
                ImageIcon Ico = new ImageIcon("SpeeckTyperIco.png");
                jDialog4.setIconImage(Ico.getImage());
          
               this.setVisible(false);
               jDialog4.setSize(262, 76);
               jDialog4.setVisible(true);
               jDialog4.setLocationRelativeTo(null);
              // System.exit(0);
           }
           else
           {
                ImageIcon Ico = new ImageIcon("SpeeckTyperIco.png");
          this.setIconImage(Ico.getImage());
           ArrayList<String> items = new ArrayList<String>();
         
         
        for(int i = 0;i<HindiWords.length;i++)
        {
            items.add(HindiWords[i]);
        }
         setupAutoComplete(jTextArea1, items);
          setLocationRelativeTo(null);
          setVisible(false);
        //  Point p = this.getLocation();
        //  lw.setLocation(p.x , p.y + this.getHeight()/2 + 20);
         
        jLabel1.setText("Loading Library........");
        jButton2.setEnabled(false);
        jButton1.setEnabled(false);
          
         (new InitThread()).start();
           }
        
         
       
     
    }//GEN-LAST:event_formWindowOpened
     private void showPopupMenu(java.awt.event.MouseEvent e)
    {
        jPopupMenu1.show(this, e.getX(), e.getY());
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         
           isStart = false;
           DSpeechTyper.DSpeechTyper.setRecognizerEnabled(false);
          
    }//GEN-LAST:event_jButton1ActionPerformed
       
    private void jTextArea1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextArea1MouseClicked

    private void jPopupMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPopupMenu1MouseClicked
                    // TODO add your handling code here:
    }//GEN-LAST:event_jPopupMenu1MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        String Str = jTextArea1.getText();
        StringSelection Selec = new StringSelection(Str);
        Clipboard Cb =  Toolkit.getDefaultToolkit().getSystemClipboard();
        Cb.setContents(Selec, Selec);
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
         String Str = jTextArea1.getSelectedText();
        StringSelection Selec = new StringSelection(Str);
        Clipboard Cb =  Toolkit.getDefaultToolkit().getSystemClipboard();
        Cb.setContents(Selec, Selec);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
         String Str =  jTextArea1.getText();
        StringSelection Selec = new StringSelection(Str);
        Clipboard Cb =  Toolkit.getDefaultToolkit().getSystemClipboard();
        Cb.setContents(Selec, Selec);
        jTextArea1.setText("");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
         jDialog1.setVisible(true);
          jDialog1.setSize(375, 393);
        jDialog1.setLocationRelativeTo(null);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jTextArea1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MousePressed
        // TODO add your handling code here:
         if(evt.isPopupTrigger())
        {
            showPopupMenu(evt);
        }
    }//GEN-LAST:event_jTextArea1MousePressed

    private void jTextArea1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextArea1MouseReleased
        // TODO add your handling code here:
         if(evt.isPopupTrigger())
        {
            showPopupMenu(evt);
        }
    }//GEN-LAST:event_jTextArea1MouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jDialog2.setVisible(true);
          jDialog2.setSize(465, 483);
        jDialog2.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTextArea1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyPressed
        // TODO add your handling code here:
        //jTextArea1.replaceRange("",  jTextArea1.getCaretPosition()-1,  jTextArea1.getCaretPosition());
     /*  if(isStart == true)
       {
           isStart = false;
       }
       if(DSpeechTyper.DSpeechTyper.isRecognizerEnabled())
       {
           DSpeechTyper.DSpeechTyper.setRecognizerEnabled(false);
           
            jButton1.setEnabled(false);
            jButton2.setEnabled(true);
       }  */
       
        String Tex  = "";
        
        cc =  jTextArea1.getCaretPosition();
        if(cc < curcer)
        {
              KeyString=  "";
              curcer = -1;
        }
        else
        {
         if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_SPACE)
         {  return;
         }
         else if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT)
         {  }
         else if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT)
         {  }
         else if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_UP)
         {  }
          else if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN)
         {  }
         else if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_BACK_SPACE)
         {
               
              if(KeyString.length() > 0)
             {
                KeyString = KeyString.substring(0, KeyString.length()-1);
                
                 cc =  jTextArea1.getCaretPosition();
                 
             }
             
             
            
         }
         else
         {
             
             if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
             {
             
                // jTextArea1.append("\n");￿￿￿￿￿
                
             }
              else
             {
               
                      KeyString += Character.toString(evt.getKeyChar()).toLowerCase();
                 
                  
                 //  jTextField1.setText(KeyString);
                  // jTextField1.setText();
                  if(curcer == -1)
                 {
                      curcer =  jTextArea1.getCaretPosition();
                 }
                
               
             }
            
              
         }
         
// jList1.setVisible(true);
         //jList1action(null, evt).
        // jDialog1.setVisible(true);
      }
        
    }//GEN-LAST:event_jTextArea1KeyPressed

    private void jTextArea1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextArea1KeyTyped

    private void jDialog4WindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jDialog4WindowClosing
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jDialog4WindowClosing

    private void jButton74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton74ActionPerformed
        // TODO add your handling code here:
        jTextArea1.insert(",", jTextArea1.getCaretPosition());
    }//GEN-LAST:event_jButton74ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jTextArea1.insert("”", jTextArea1.getCaretPosition());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         jTextArea1.insert("“", jTextArea1.getCaretPosition());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
          jTextArea1.insert("|", jTextArea1.getCaretPosition());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        
         String Str =  jTextArea1.getText();
        StringSelection Selec = new StringSelection(Str);
        Clipboard Cb =  Toolkit.getDefaultToolkit().getSystemClipboard();
        Cb.setContents(Selec, Selec);
    }//GEN-LAST:event_formWindowClosing
         public static void main(String args[]) {
          try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SpeechTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SpeechTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SpeechTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SpeechTyper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new SpeechTyper().setVisible(true);
                //setVisible(false);
               
            }
           
        });
            
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton74;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
