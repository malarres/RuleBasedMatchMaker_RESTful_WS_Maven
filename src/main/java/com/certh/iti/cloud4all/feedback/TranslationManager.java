package com.certh.iti.cloud4all.feedback;

/**
 *
 * @author nkak
 */
public class TranslationManager 
{
    public static final int ENGLISH = 0;
    public static final int GERMAN = 1;
    public static final int GREEK = 2;
    public static final int SPANISH = 3;
    private int currentLanguage;
    
    public static final String ENCODING_EN = "utf-8";
    public static final String ENCODING_DE = "utf-8";
    public static final String ENCODING_GR = "iso-8859-7";
    public static final String ENCODING_SP = "utf-8";
    public String ENCODING;
    
    public static final String GO_TO_CONTENT_EN = "go to content";
    public static final String GO_TO_CONTENT_DE = "Gehe zum Inhalt";
    public static final String GO_TO_CONTENT_GR = "μετάβαση στο περιεχόμενο";
    public static final String GO_TO_CONTENT_SP = "Ir al contenido";
    public String GO_TO_CONTENT;
    
    public static final String GO_TO_NAVIGATION_EN = "go to navigation";
    public static final String GO_TO_NAVIGATION_DE = "Gehe zur Navigation";
    public static final String GO_TO_NAVIGATION_GR = "μετάβαση στην περιήγηση";
    public static final String GO_TO_NAVIGATION_SP = "Ir a la navegación";
    public String GO_TO_NAVIGATION;
    
    public static final String YOUR_ADAPTATIONS_EN = "Your Adaptations";
    public static final String YOUR_ADAPTATIONS_DE = "Deine Anpassungen";
    public static final String YOUR_ADAPTATIONS_GR = "Οι προσαρμογές σας";
    public static final String YOUR_ADAPTATIONS_SP = "Tus adaptaciones";
    public String YOUR_ADAPTATIONS;
    
    public static final String WE_HAVE_LAUNCHED_EN = "We have launched ";
    public static final String WE_HAVE_LAUNCHED_DE = "Gestartet haben wir ";
    public static final String WE_HAVE_LAUNCHED_GR = "Έχουμε ξεκινήσει την εφαρμογή ";
    public static final String WE_HAVE_LAUNCHED_SP = "Hemos iniciado ";
    public String WE_HAVE_LAUNCHED;
    
    public static final String FOR_YOU_EN = " for you.";
    public static final String FOR_YOU_DE = " für Dich";
    public static final String FOR_YOU_GR = " για εσάς.";
    public static final String FOR_YOU_SP = " para ti.";
    public String FOR_YOU;
    
    public static final String THE_FOLLOWING_PREFS_EN = "The following preferences have been configured for you: ";
    public static final String THE_FOLLOWING_PREFS_DE = "Folgende Einstellungen konnten wir für Dich konfigurieren: ";
    public static final String THE_FOLLOWING_PREFS_GR = "Οι ακόλουθες προτιμήσεις έχουν ρυθμιστεί για εσάς: ";
    public static final String THE_FOLLOWING_PREFS_SP = "Las siguientes preferencias han sido configuradas para ti: ";
    public String THE_FOLLOWING_PREFS;
    
    public static final String HELP_EN = "Help";
    public static final String HELP_DE = "Hilfe";
    public static final String HELP_GR = "Βοήθεια";
    public static final String HELP_SP = "Ayuda";
    public String HELP;
    
    public static final String WE_HAVE_NEVER_EN = "We have never launched ";
    public static final String WE_HAVE_NEVER_DE = "Wir haben noch nich ";
    public static final String WE_HAVE_NEVER_GR = "Δεν έχουμε ξαναεκτελέσει την εφαρμογή ";
    public static final String WE_HAVE_NEVER_SP = "Nunca hemos iniciado ";
    public String WE_HAVE_NEVER;
    
    public static final String FOR_YOU_BEFORE_EN = " for you before. The following information about shortcuts might be helpful for you.";
    public static final String FOR_YOU_BEFORE_DE = " für Dich gestartet. Folgende Informationen über Tastenkombinationen könnten für Dich hilfreich sein.";
    public static final String FOR_YOU_BEFORE_GR = " για εσάς στο παρελθόν. Οι ακόλουθες πληροφορίες σχετικά με τις συντομεύσεις μπορεί να σας φανούν χρήσιμες.";
    public static final String FOR_YOU_BEFORE_SP = " para ti antes. La siguiente información sobre atajos puede ser de utilidad.";
    public String FOR_YOU_BEFORE;
    
    public static final String SHORTCUTS_EN = "Shortcuts";
    public static final String SHORTCUTS_DE = "Tastenkombinationen";
    public static final String SHORTCUTS_GR = "Συντομεύσεις";
    public static final String SHORTCUTS_SP = "Atajos/Accesos directos";
    public String SHORTCUTS;
    
    public static final String GENERAL_COMMANDS_EN = "General commands";
    public static final String GENERAL_COMMANDS_DE = "Allgemeine Befehle";
    public static final String GENERAL_COMMANDS_GR = "Γενικές εντολές";
    public static final String GENERAL_COMMANDS_SP = "Acciones comunes";
    public String GENERAL_COMMANDS;
    
    public static final String GENERAL_COMMANDS_SUMMARY_EN = "This tables contains most general commands for the assistive technology we launched for you.";
    public static final String GENERAL_COMMANDS_SUMMARY_DE = "Diese Tabelle beinhaltet allgemeine Befehle für das Hilfsmittel, welches wir für Dich gestartet haben.";
    public static final String GENERAL_COMMANDS_SUMMARY_GR = "Αυτός ο πίνακας περιλαμβάνει τις πιο γενικές εντολές για την εφαρμογή που εκτελέσαμε για εσάς.";
    public static final String GENERAL_COMMANDS_SUMMARY_SP = "Esta tabla contiene los controles más usuales para el producto de apoyo que hemos iniciado para ti.";
    public String GENERAL_COMMANDS_SUMMARY;
    
    public static final String DESKTOP_LAYOUT_EN = "Desktop layout";
    public static final String DESKTOP_LAYOUT_DE = "Desktop Tastaturlayout";
    public static final String DESKTOP_LAYOUT_GR = "Διάταξη desktop";
    public static final String DESKTOP_LAYOUT_SP = "Perfil de escritorio";
    public String DESKTOP_LAYOUT;
    
    public static final String LAPTOP_LAYOUT_EN = "Laptop layout";
    public static final String LAPTOP_LAYOUT_DE = "Laptop Tastaturlayout";
    public static final String LAPTOP_LAYOUT_GR = "Διάταξη laptop";
    public static final String LAPTOP_LAYOUT_SP = "Perfil de portátil";
    public String LAPTOP_LAYOUT;
    
    public static final String DEFAULT_GENERAL_COMMANDS_EN = "Default short cuts of general commands for ";
    public static final String DEFAULT_GENERAL_COMMANDS_DE = "Standard Tastaturbefehle allgemeiner Befehle ";
    public static final String DEFAULT_GENERAL_COMMANDS_GR = "Προεπιλεγμένες συντομεύσεις δρομέα (system caret commands) για την εφαρμογή ";
    public static final String DEFAULT_GENERAL_COMMANDS_SP = "Atajos de teclado por defecto de acciones comunes para ";
    public String DEFAULT_GENERAL_COMMANDS;
    
    public static final String SYSTEM_CARET_COMMANDS_EN = "System caret commands";
    public static final String SYSTEM_CARET_COMMANDS_DE = "für die Einfügemarke (Cursor)";
    public static final String SYSTEM_CARET_COMMANDS_GR = "Εντολές δρομέα (system caret commands)";
    public static final String SYSTEM_CARET_COMMANDS_SP = "Órdenes del cursor de inserción";
    public String SYSTEM_CARET_COMMANDS;
    
    public static final String SYSTEM_CARET_SUMMARY_COMMANDS_EN = "This tables contains most common system caret commands for the assistive technology we launched for you.";
    public static final String SYSTEM_CARET_SUMMARY_COMMANDS_DE = "Diese Tabelle behinhaltet gängige Befehle zur Einfügemarke für das Hilfsmittel welches wir für Dich gestartet haben.";
    public static final String SYSTEM_CARET_SUMMARY_COMMANDS_GR = "Αυτός ο πίνακας περιλαμβάνει τις πιο συνηθισμένες εντολές δρομέα (system caret commands) για την εφαρμογή που εκτελέσαμε για εσάς.";
    public static final String SYSTEM_CARET_SUMMARY_COMMANDS_SP = "This tables contains most common system caret commands for the assistive technology we launched for you.";
    public String SYSTEM_CARET_SUMMARY_COMMANDS;
    
    public static final String DEFAULT_SYSTEM_CARET_COMMANDS_EN = "Default short cuts of system caret commands for ";
    public static final String DEFAULT_SYSTEM_CARET_COMMANDS_DE = "Standard  Tastaturbefehle der Einfügemarke für ";
    public static final String DEFAULT_SYSTEM_CARET_COMMANDS_GR = "Προεπιλεγμένες συντομεύσεις δρομέα (system caret commands) για την εφαρμογή ";
    public static final String DEFAULT_SYSTEM_CARET_COMMANDS_SP = "Default short cuts of system caret commands for ";
    public String DEFAULT_SYSTEM_CARET_COMMANDS;
    
    public static final String REVIEW_COMMANDS_EN = "Review commands";
    public static final String REVIEW_COMMANDS_DE = "Review Befehle";
    public static final String REVIEW_COMMANDS_GR = "Εντολές αναθεώρησης";
    public static final String REVIEW_COMMANDS_SP = "Review commands";
    public String REVIEW_COMMANDS;
    
    public static final String REVIEW_COMMANDS_SUMMARY_EN = "This tables contains most common review commands for the assistive technology we launched for you.";
    public static final String REVIEW_COMMANDS_SUMMARY_DE = "Diese Tabelle beinhaltet gängige Befehle für dem Reviewmodus für das Hilfsmittel welches wir für Dich gestartet haben.";
    public static final String REVIEW_COMMANDS_SUMMARY_GR = "Αυτός ο πίνακας περιλαμβάνει τις πιο συνηθισμένες εντολές αναθεώρησης για την εφαρμογή που εκτελέσαμε για εσάς.";
    public static final String REVIEW_COMMANDS_SUMMARY_SP = "This tables contains most common review commands for the assistive technology we launched for you.";
    public String REVIEW_COMMANDS_SUMMARY;
    
    public static final String DEFAULT_REVIEW_COMMANDS_EN = "Default short cuts of review commands for ";
    public static final String DEFAULT_REVIEW_COMMANDS_DE = "Standard Tastaturbefehle für den Reviewmodus ";
    public static final String DEFAULT_REVIEW_COMMANDS_GR = "Προεπιλεγμένες συντομεύσεις εντολών αναθεώρησης για την εφαρμογή ";
    public static final String DEFAULT_REVIEW_COMMANDS_SP = "Default short cuts of review commands for ";
    public String DEFAULT_REVIEW_COMMANDS;
    
    public static final String BROWSER_COMMANDS_EN = "Browser commands";
    public static final String BROWSER_COMMANDS_DE = "Browser Befehle";
    public static final String BROWSER_COMMANDS_GR = "Εντολές περιήγησης";
    public static final String BROWSER_COMMANDS_SP = "Atajos de navegación";
    public String BROWSER_COMMANDS;
    
    public static final String BROWSER_COMMANDS_SUMMARY_EN = "This tables contains most common browser commands for the assistive technology we launched for you.";
    public static final String BROWSER_COMMANDS_SUMMARY_DE = "Diese Tabelle beinhaltet gängige Browserbefehle für das Hilfsmittel welches wir für Dich gestartet haben.";
    public static final String BROWSER_COMMANDS_SUMMARY_GR = "Αυτός ο πίνακας περιλαμβάνει τις πιο συνηθισμένες εντολές περιήγησης για την εφαρμογή που εκτελέσαμε για εσάς.";
    public static final String BROWSER_COMMANDS_SUMMARY_SP = "Esta tabla contiene los atajos de navegación más comunes para el producto de apoyo que hemos iniciado para ti.";
    public String BROWSER_COMMANDS_SUMMARY;
    
    public static final String DEFAULT_BROWSER_COMMANDS_EN = "Default short cuts of browser commands for ";
    public static final String DEFAULT_BROWSER_COMMANDS_DE = "Standard Tastaturbefehle für den Internet Browser ";
    public static final String DEFAULT_BROWSER_COMMANDS_GR = "Προεπιλεγμένες συντομεύσεις εντολών περιήγησης για την εφαρμογή ";
    public static final String DEFAULT_BROWSER_COMMANDS_SP = "Atajos de teclado por defecto de los comandos de navegación para ";
    public String DEFAULT_BROWSER_COMMANDS;
    
    public static final String TABLE_COMMANDS_EN = "Table commands";
    public static final String TABLE_COMMANDS_DE = "Tabellen Befehle";
    public static final String TABLE_COMMANDS_GR = "Εντολές πινάκων";
    public static final String TABLE_COMMANDS_SP = "Table commands";
    public String TABLE_COMMANDS;
    
    public static final String TABLE_COMMANDS_SUMMARY_EN = "This tables contains most common table commands for the assistive technology we launched for you.";
    public static final String TABLE_COMMANDS_SUMMARY_DE = "Diese Tabelle beinhaltet gängige Tabellen Befehle für das Hilfsmittel welches wir für Dich gestartet haben.";
    public static final String TABLE_COMMANDS_SUMMARY_GR = "Αυτός ο πίνακας περιλαμβάνει τις πιο συνηθισμένες εντολές πινάκων για την εφαρμογή που εκτελέσαμε για εσάς.";
    public static final String TABLE_COMMANDS_SUMMARY_SP = "This tables contains most common table commands for the assistive technology we launched for you.";
    public String TABLE_COMMANDS_SUMMARY;
    
    public static final String DEFAULT_TABLE_COMMANDS_EN = "Default short cuts of table commands for ";
    public static final String DEFAULT_TABLE_COMMANDS_DE = "Standard Tastaturbefehle für Tabellen für ";
    public static final String DEFAULT_TABLE_COMMANDS_GR = "Προεπιλεγμένες συντομεύσεις εντολών πινάκων για την εφαρμογή ";
    public static final String DEFAULT_TABLE_COMMANDS_SP = "Default short cuts of table commands for ";
    public String DEFAULT_TABLE_COMMANDS;
    
    public static final String USER_TUTORIAL_EN = "User tutorial";
    public static final String USER_TUTORIAL_DE = "Handbuch";
    public static final String USER_TUTORIAL_GR = "Εγχειρίδιο χρήσης";
    public static final String USER_TUTORIAL_SP = "Tutorial para usuarios";
    public String USER_TUTORIAL;
    
    public static final String USER_TUTORIAL_AVAILABLE_EN = "Full user tutorial is available: ";
    public static final String USER_TUTORIAL_AVAILABLE_DE = "Komplette Handbuch ist verfügbar unter: ";
    public static final String USER_TUTORIAL_AVAILABLE_GR = "Πλήρες εγχειρίδιο χρήσης διατίθεται εδώ: ";
    public static final String USER_TUTORIAL_AVAILABLE_SP = "El tutorial para usuarios al completo se encuentra disponible: ";
    public String USER_TUTORIAL_AVAILABLE;
    
    public static final String FURTHER_RECOMMENDATIONS_EN = "Further Recommendations";
    public static final String FURTHER_RECOMMENDATIONS_DE = "Weitere Empfehlungen";
    public static final String FURTHER_RECOMMENDATIONS_GR = "Περαιτέρω συστάσεις";
    public static final String FURTHER_RECOMMENDATIONS_SP = "Más recomendaciones";
    public String FURTHER_RECOMMENDATIONS;
    
    public static final String ALTERNATIVE_SOLUTIONS_EN = "Following alternative solutions that suit your needs and preferences is also installed on the device: ";
    public static final String ALTERNATIVE_SOLUTIONS_DE = "Die Folgenden alternativen Lösungen, welche ebenso Deinen Bedürfnissen und Vorstellungen entsprechen, sind auch auf dem System installiert: ";
    public static final String ALTERNATIVE_SOLUTIONS_GR = "Οι παρακάτω εναλλακτικές εφαρμογές που ταιριάζουν στις ανάγκες και προτιμήσεις σας είναι επίσης εγκατεστημένες: ";
    public static final String ALTERNATIVE_SOLUTIONS_SP = "En el dispositivo también se encuentran instaladas las siguientes soluciones alternativas que acomodan (tus|sus) necesidades y preferencias: ";
    public String ALTERNATIVE_SOLUTIONS;
    
    public static final String WANT_TO_LAUNCH_EN = "Do you want to launch ";
    public static final String WANT_TO_LAUNCH_DE = "Möchtest Du starten ";
    public static final String WANT_TO_LAUNCH_GR = "Θα θέλατε να εκτελέσετε την εφαρμογή ";
    public static final String WANT_TO_LAUNCH_SP = "¿Quieres iniciar ";
    public String WANT_TO_LAUNCH;
    
    public static final String WANT_TO_LAUNCH_ONE_OF_EN = "Do you want to launch any of the following applications: ";
    public static final String WANT_TO_LAUNCH_ONE_OF_DE = "Möchtest Du eine der folgenden Anwednungen starten: ";
    public static final String WANT_TO_LAUNCH_ONE_OF_GR = "Θα θέλατε να εκτελέσετε κάποια από τις παρακάτω εφαρμογές: ";
    public static final String WANT_TO_LAUNCH_ONE_OF_SP = "¿Quieres iniciar alguna de las siguientes aplicaciones? ";
    public String WANT_TO_LAUNCH_ONE_OF;
    
    public static final String INSTEAD_OF_EN = " instead of ";
    public static final String INSTEAD_OF_DE = " statt ";
    public static final String INSTEAD_OF_GR = " αντί για την εφαρμογή ";
    public static final String INSTEAD_OF_SP = " en lugar de ";
    public String INSTEAD_OF;
    
    public static final String QUESTIONMARK_EN = "?";
    public static final String QUESTIONMARK_DE = "?";
    public static final String QUESTIONMARK_GR = ";";
    public static final String QUESTIONMARK_SP = "?";
    public String QUESTIONMARK;
    
    
    private static TranslationManager instance = null;
    
    private TranslationManager() 
    {
    }
    
    public static TranslationManager getInstance() 
    {
        if(instance == null) 
            instance = new TranslationManager();
        return instance;
    }
    
    public void setLanguage(int tmpLanguage)
    {
        if(tmpLanguage == ENGLISH)
        {
            ENCODING = ENCODING_EN;
            GO_TO_CONTENT = GO_TO_CONTENT_EN;
            GO_TO_NAVIGATION = GO_TO_NAVIGATION_EN;
            YOUR_ADAPTATIONS = YOUR_ADAPTATIONS_EN;
            WE_HAVE_LAUNCHED = WE_HAVE_LAUNCHED_EN;
            FOR_YOU = FOR_YOU_EN;
            THE_FOLLOWING_PREFS = THE_FOLLOWING_PREFS_EN;
            HELP = HELP_EN;
            WE_HAVE_NEVER = WE_HAVE_NEVER_EN;
            FOR_YOU_BEFORE = FOR_YOU_BEFORE_EN;
            SHORTCUTS = SHORTCUTS_EN;
            GENERAL_COMMANDS = GENERAL_COMMANDS_EN;
            GENERAL_COMMANDS_SUMMARY = GENERAL_COMMANDS_SUMMARY_EN;
            DESKTOP_LAYOUT = DESKTOP_LAYOUT_EN;
            LAPTOP_LAYOUT = LAPTOP_LAYOUT_EN;
            DEFAULT_GENERAL_COMMANDS = DEFAULT_GENERAL_COMMANDS_EN;
            SYSTEM_CARET_COMMANDS = SYSTEM_CARET_COMMANDS_EN;
            SYSTEM_CARET_SUMMARY_COMMANDS = SYSTEM_CARET_SUMMARY_COMMANDS_EN;
            DEFAULT_SYSTEM_CARET_COMMANDS = DEFAULT_SYSTEM_CARET_COMMANDS_EN;
            REVIEW_COMMANDS = REVIEW_COMMANDS_EN;
            REVIEW_COMMANDS_SUMMARY = REVIEW_COMMANDS_SUMMARY_EN;
            DEFAULT_REVIEW_COMMANDS = DEFAULT_REVIEW_COMMANDS_EN;
            BROWSER_COMMANDS = BROWSER_COMMANDS_EN;
            BROWSER_COMMANDS_SUMMARY = BROWSER_COMMANDS_SUMMARY_EN;
            DEFAULT_BROWSER_COMMANDS = DEFAULT_BROWSER_COMMANDS_EN;
            TABLE_COMMANDS = TABLE_COMMANDS_EN;
            TABLE_COMMANDS_SUMMARY = TABLE_COMMANDS_SUMMARY_EN;
            DEFAULT_TABLE_COMMANDS = DEFAULT_TABLE_COMMANDS_EN;
            USER_TUTORIAL = USER_TUTORIAL_EN;
            USER_TUTORIAL_AVAILABLE = USER_TUTORIAL_AVAILABLE_EN;
            FURTHER_RECOMMENDATIONS = FURTHER_RECOMMENDATIONS_EN;
            ALTERNATIVE_SOLUTIONS = ALTERNATIVE_SOLUTIONS_EN;
            WANT_TO_LAUNCH = WANT_TO_LAUNCH_EN;
            WANT_TO_LAUNCH_ONE_OF = WANT_TO_LAUNCH_ONE_OF_EN;
            INSTEAD_OF = INSTEAD_OF_EN;
            QUESTIONMARK = QUESTIONMARK_EN;
        }
        else if(tmpLanguage == GERMAN)
        {
            ENCODING = ENCODING_DE;
            GO_TO_CONTENT = GO_TO_CONTENT_DE;
            GO_TO_NAVIGATION = GO_TO_NAVIGATION_DE;
            YOUR_ADAPTATIONS = YOUR_ADAPTATIONS_DE;
            WE_HAVE_LAUNCHED = WE_HAVE_LAUNCHED_DE;
            FOR_YOU = FOR_YOU_DE;
            THE_FOLLOWING_PREFS = THE_FOLLOWING_PREFS_DE;
            HELP = HELP_DE;
            WE_HAVE_NEVER = WE_HAVE_NEVER_DE;
            FOR_YOU_BEFORE = FOR_YOU_BEFORE_DE;
            SHORTCUTS = SHORTCUTS_DE;
            GENERAL_COMMANDS = GENERAL_COMMANDS_DE;
            GENERAL_COMMANDS_SUMMARY = GENERAL_COMMANDS_SUMMARY_DE;
            DESKTOP_LAYOUT = DESKTOP_LAYOUT_DE;
            LAPTOP_LAYOUT = LAPTOP_LAYOUT_DE;
            DEFAULT_GENERAL_COMMANDS = DEFAULT_GENERAL_COMMANDS_DE;
            SYSTEM_CARET_COMMANDS = SYSTEM_CARET_COMMANDS_DE;
            SYSTEM_CARET_SUMMARY_COMMANDS = SYSTEM_CARET_SUMMARY_COMMANDS_DE;
            DEFAULT_SYSTEM_CARET_COMMANDS = DEFAULT_SYSTEM_CARET_COMMANDS_DE;
            REVIEW_COMMANDS = REVIEW_COMMANDS_DE;
            REVIEW_COMMANDS_SUMMARY = REVIEW_COMMANDS_SUMMARY_DE;
            DEFAULT_REVIEW_COMMANDS = DEFAULT_REVIEW_COMMANDS_DE;
            BROWSER_COMMANDS = BROWSER_COMMANDS_DE;
            BROWSER_COMMANDS_SUMMARY = BROWSER_COMMANDS_SUMMARY_DE;
            DEFAULT_BROWSER_COMMANDS = DEFAULT_BROWSER_COMMANDS_DE;
            TABLE_COMMANDS = TABLE_COMMANDS_DE;
            TABLE_COMMANDS_SUMMARY = TABLE_COMMANDS_SUMMARY_DE;
            DEFAULT_TABLE_COMMANDS = DEFAULT_TABLE_COMMANDS_DE;
            USER_TUTORIAL = USER_TUTORIAL_DE;
            USER_TUTORIAL_AVAILABLE = USER_TUTORIAL_AVAILABLE_DE;
            FURTHER_RECOMMENDATIONS = FURTHER_RECOMMENDATIONS_DE;
            ALTERNATIVE_SOLUTIONS = ALTERNATIVE_SOLUTIONS_DE;
            WANT_TO_LAUNCH = WANT_TO_LAUNCH_DE;
            WANT_TO_LAUNCH_ONE_OF = WANT_TO_LAUNCH_ONE_OF_DE;
            INSTEAD_OF = INSTEAD_OF_DE;
            QUESTIONMARK = QUESTIONMARK_DE;
        }
        else if(tmpLanguage == GREEK)
        {
            ENCODING = ENCODING_GR;
            GO_TO_CONTENT = GO_TO_CONTENT_GR;
            GO_TO_NAVIGATION = GO_TO_NAVIGATION_GR;
            YOUR_ADAPTATIONS = YOUR_ADAPTATIONS_GR;
            WE_HAVE_LAUNCHED = WE_HAVE_LAUNCHED_GR;
            FOR_YOU = FOR_YOU_GR;
            THE_FOLLOWING_PREFS = THE_FOLLOWING_PREFS_GR;
            HELP = HELP_GR;
            WE_HAVE_NEVER = WE_HAVE_NEVER_GR;
            FOR_YOU_BEFORE = FOR_YOU_BEFORE_GR;
            SHORTCUTS = SHORTCUTS_GR;
            GENERAL_COMMANDS = GENERAL_COMMANDS_GR;
            GENERAL_COMMANDS_SUMMARY = GENERAL_COMMANDS_SUMMARY_GR;
            DESKTOP_LAYOUT = DESKTOP_LAYOUT_GR;
            LAPTOP_LAYOUT = LAPTOP_LAYOUT_GR;
            DEFAULT_GENERAL_COMMANDS = DEFAULT_GENERAL_COMMANDS_GR;
            SYSTEM_CARET_COMMANDS = SYSTEM_CARET_COMMANDS_GR;
            SYSTEM_CARET_SUMMARY_COMMANDS = SYSTEM_CARET_SUMMARY_COMMANDS_GR;
            DEFAULT_SYSTEM_CARET_COMMANDS = DEFAULT_SYSTEM_CARET_COMMANDS_GR;
            REVIEW_COMMANDS = REVIEW_COMMANDS_GR;
            REVIEW_COMMANDS_SUMMARY = REVIEW_COMMANDS_SUMMARY_GR;
            DEFAULT_REVIEW_COMMANDS = DEFAULT_REVIEW_COMMANDS_GR;
            BROWSER_COMMANDS = BROWSER_COMMANDS_GR;
            BROWSER_COMMANDS_SUMMARY = BROWSER_COMMANDS_SUMMARY_GR;
            DEFAULT_BROWSER_COMMANDS = DEFAULT_BROWSER_COMMANDS_GR;
            TABLE_COMMANDS = TABLE_COMMANDS_GR;
            TABLE_COMMANDS_SUMMARY = TABLE_COMMANDS_SUMMARY_GR;
            DEFAULT_TABLE_COMMANDS = DEFAULT_TABLE_COMMANDS_GR;
            USER_TUTORIAL = USER_TUTORIAL_GR;
            USER_TUTORIAL_AVAILABLE = USER_TUTORIAL_AVAILABLE_GR;
            FURTHER_RECOMMENDATIONS = FURTHER_RECOMMENDATIONS_GR;
            ALTERNATIVE_SOLUTIONS = ALTERNATIVE_SOLUTIONS_GR;
            WANT_TO_LAUNCH = WANT_TO_LAUNCH_GR;
            WANT_TO_LAUNCH_ONE_OF = WANT_TO_LAUNCH_ONE_OF_GR;
            INSTEAD_OF = INSTEAD_OF_GR;
            QUESTIONMARK = QUESTIONMARK_GR;
        }
        else if(tmpLanguage == SPANISH)
        {
            ENCODING = ENCODING_SP;
            GO_TO_CONTENT = GO_TO_CONTENT_SP;
            GO_TO_NAVIGATION = GO_TO_NAVIGATION_SP;
            YOUR_ADAPTATIONS = YOUR_ADAPTATIONS_SP;
            WE_HAVE_LAUNCHED = WE_HAVE_LAUNCHED_SP;
            FOR_YOU = FOR_YOU_SP;
            THE_FOLLOWING_PREFS = THE_FOLLOWING_PREFS_SP;
            HELP = HELP_SP;
            WE_HAVE_NEVER = WE_HAVE_NEVER_SP;
            FOR_YOU_BEFORE = FOR_YOU_BEFORE_SP;
            SHORTCUTS = SHORTCUTS_SP;
            GENERAL_COMMANDS = GENERAL_COMMANDS_SP;
            GENERAL_COMMANDS_SUMMARY = GENERAL_COMMANDS_SUMMARY_SP;
            DESKTOP_LAYOUT = DESKTOP_LAYOUT_SP;
            LAPTOP_LAYOUT = LAPTOP_LAYOUT_SP;
            DEFAULT_GENERAL_COMMANDS = DEFAULT_GENERAL_COMMANDS_SP;
            SYSTEM_CARET_COMMANDS = SYSTEM_CARET_COMMANDS_SP;
            SYSTEM_CARET_SUMMARY_COMMANDS = SYSTEM_CARET_SUMMARY_COMMANDS_SP;
            DEFAULT_SYSTEM_CARET_COMMANDS = DEFAULT_SYSTEM_CARET_COMMANDS_SP;
            REVIEW_COMMANDS = REVIEW_COMMANDS_SP;
            REVIEW_COMMANDS_SUMMARY = REVIEW_COMMANDS_SUMMARY_SP;
            DEFAULT_REVIEW_COMMANDS = DEFAULT_REVIEW_COMMANDS_SP;
            BROWSER_COMMANDS = BROWSER_COMMANDS_SP;
            BROWSER_COMMANDS_SUMMARY = BROWSER_COMMANDS_SUMMARY_SP;
            DEFAULT_BROWSER_COMMANDS = DEFAULT_BROWSER_COMMANDS_SP;
            TABLE_COMMANDS = TABLE_COMMANDS_SP;
            TABLE_COMMANDS_SUMMARY = TABLE_COMMANDS_SUMMARY_SP;
            DEFAULT_TABLE_COMMANDS = DEFAULT_TABLE_COMMANDS_SP;
            USER_TUTORIAL = USER_TUTORIAL_SP;
            USER_TUTORIAL_AVAILABLE = USER_TUTORIAL_AVAILABLE_SP;
            FURTHER_RECOMMENDATIONS = FURTHER_RECOMMENDATIONS_SP;
            ALTERNATIVE_SOLUTIONS = ALTERNATIVE_SOLUTIONS_SP;
            WANT_TO_LAUNCH = WANT_TO_LAUNCH_SP;
            WANT_TO_LAUNCH_ONE_OF = WANT_TO_LAUNCH_ONE_OF_SP;
            INSTEAD_OF = INSTEAD_OF_SP;
            QUESTIONMARK = QUESTIONMARK_SP;
        }
        currentLanguage = tmpLanguage;
    }
    
    public int getCurrentLanguage()
    {
        return currentLanguage;
    }
}