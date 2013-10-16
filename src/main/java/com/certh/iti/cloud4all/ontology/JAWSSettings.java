package com.certh.iti.cloud4all.ontology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author nkak
 */
public class JAWSSettings 
{
    String SharedFiles;
    String UserFiles;
    Integer UserOptionsGroup_ProgressBarUpdateInterval;
    Integer UserOptionsGroup_TypingEcho;
    Integer UserOptionsGroup_bVirtViewer;
    Integer UserOptionsGroup_ScreenEcho;
    Integer UserOptionsGroup_TypingInterrupt;
    String UserOptionsGroup_InsertKeyMode;
    Integer UserOptionsGroup_KeyRepeat;
    Integer WebHTMLPDFGroup_SkipPastRepeatedText;
    Integer WebHTMLPDFGroup_BlockQuoteIndication;
    Integer WebHTMLPDFGroup_ExpandAbbreviations;
    Integer WebHTMLPDFGroup_ExpandAcronyms;
    Boolean WebHTMLPDFGroup_SpeakAccessKeysWithinWebPages;
    Boolean WebHTMLPDFGroup_CustomPlaceMarkerPageSummary;
    Integer WebHTMLPDFGroup_TextBlockLenght;
    Integer WebHTMLPDFGroup_LinesPerDefault;
    Integer WebHTMLPDFGroup_MaxLineLength;
    Integer WebHTMLPDFGroup_DocumentPresentationMode;
    Integer WebHTMLPDFGroup_ScreenFollowVCursor;
    Integer WebHTMLPDFGroup_WrapNavigation;
    Integer WebHTMLPDFGroup_IncludeGraphics;
    Integer WebHTMLPDFGroup_GraphicRenderingOption;
    Integer WebHTMLPDFGroup_GraphicalLinkLastResort;
    Integer WebHTMLPDFGroup_FilterConsecutiveDuplicateLinks;
    Integer WebHTMLPDFGroup_IdentifyLinkType;
    Integer WebHTMLPDFGroup_IdentifySamePageLink;
    Integer WebHTMLPDFGroup_LinkText;
    Integer WebHTMLPDFGroup_IncludeGraphicLinks;
    Integer WebHTMLPDFGroup_IncludeImageMapLinks;
    Integer WebHTMLPDFGroup_HeadingIndication;
    Integer WebHTMLPDFGroup_FrameIndication;
    Integer WebHTMLPDFGroup_IgnoreInlineFrames;
    Integer WebHTMLPDFGroup_ListIndication;
    Integer WebHTMLPDFGroup_IndicateColSpan;
    Integer WebHTMLPDFGroup_TableDetection;
    Integer WebHTMLPDFGroup_EmbeddedActiveXSupport;
    Integer WebHTMLPDFGroup_FormFieldPromptOptions;
    Integer WebHTMLPDFGroup_PageRefreshFilter;
    Integer WebHTMLPDFGroup_IndicateElementAttribute;
    Integer WebHTMLPDFGroup_ButtonTextOptions;
    Integer FormModeGroup_AutoFormsMode;
    Integer FormModeGroup_AtuFormsModeThreshold;
    Integer FormModeGroup_FormsModeAutoOff;
    Integer FormModeGroup_IndicateFormsModeWithSounds;
    String FormModeGroup_EnterFormsModeSound;
    String FormModeGroup_ExitFormsModeSound;
    String TextProcessingGroup_FilterRepeatedCharacters;
    String TextProcessingGroup_IndicateCapitalization;
    String TextProcessingGroup_ListItem;
    String TextProcessingGroup_SpellAlphanumericData;
    String TextProcessingGroup_IndicateNewLinesWhen;
    Boolean TextProcessingGroup_IndicateTables;
    Boolean TextProcessingGroup_SmartWordReading;
    Boolean TextProcessingGroup_MixedCaseProcessing;
    Boolean TextProcessingGroup_UseDictionary;
    Boolean TextProcessingGroup_SpellPhoneticallyAlways;
    Boolean TextProcessingGroup_DetectLanguages;
    Boolean TextProcessingGroup_GeneralizedDialect;
    Boolean TextProcessingGroup_EnhancedEditSupport;
    Boolean TextProcessingGroup_SayItemStateFirst;
    Boolean TextProcessingGroup_SayWindowTypeFirst;
    Boolean TextProcessingGroup_SayWindowStateFirst;
    Boolean TextProcessingGroup_SayIndentedCharacters;
    String TextProcessingGroup_NumericDateProcessing;
    String TextProcessingGroup_NumberProcessing;
    String TextProcessingGroup_SpeakSingleDigitsIfNumberContains;
    Boolean TextProcessingGroup_IfNumberContainsDashes;
    Boolean TextProcessingGroup_SpeakDollars;
    String OtherSettings_VerbosityLevel;
    String OtherSettings_SpeechAndSoundSchemes;
    String OtherSettings_SayAll;
    String OtherSettings_GraphicsAndSymbols;
    String OtherSettings_Braille;
    String OtherSettings_CaretAndCursor;
    String OtherSettings_Synthesizer;
    String OtherSettings_Punctuation;
    String OtherSettings_VoiceAliases;
    String OtherSettings_WindowClasses;
    String OtherSettings_Keyboard;
    String OtherSettings_CustomHighlight;

    
    
    
    
    
    
    String OtherSettings_ResearchIt;
    String OtherSettings_TextAnalyzer;
    String OtherSettings_ConventientOCR;
    String OtherSettings_Miscellaneous_SleppMode;
    Boolean OtherSettings_Miscellaneous_SearchForPrompts;
    Boolean OtherSettings_Miscellaneous_UseVirtualRibbonMenu;
    Boolean OtherSettings_Miscellaneous_UseVirtualPCCursor;
    String OtherSettings_Miscellaneous_TrackFocusRectanle;
    Integer OtherSettings_Miscellaneous_TextOutDelay;
    Integer OtherSettings_Miscellaneous_PixelsPerSpace;
    Integer OtherSettings_Miscellaneous_PixelsPerTab;
    Integer OtherSettings_Miscellaneous_LeftMargin;
    Boolean OtherSettings_Miscellaneous_UnderlineProximity;
    Boolean OtherSettings_Miscellaneous_ClickTolerance;
    Boolean OtherSettings_Miscellaneous_RelyOnMSAA;
    String OtherSettings_Miscellaneous_MSAAMode;
    String PlaceMarkers;
    String ConfigurationFilesExtension;
    String DictionaryFilesExtension;
    String GraphicLabelFilesExtension;
    String KeyboardMappingFilesExtension;
    String BrailleSettingFilesExtension;
    String SpeechAndSoundSchemeFilesExtension;
    String ScriptSourceFilesExtension;
    String ScriptCompiledSourceFilesExtension;
    String ScriptMessagesFilesExtension;
    String ScriptHelpFiles;
    
    public JAWSSettings()
    {
        SharedFiles = "";
        UserFiles = "";
        UserOptionsGroup_ProgressBarUpdateInterval = -1;
        UserOptionsGroup_TypingEcho = -1;
        UserOptionsGroup_bVirtViewer = -1;
        UserOptionsGroup_ScreenEcho = -1;
        UserOptionsGroup_TypingInterrupt = -1;
        UserOptionsGroup_InsertKeyMode = "";
        UserOptionsGroup_KeyRepeat = -1;
        WebHTMLPDFGroup_SkipPastRepeatedText = -1;
        WebHTMLPDFGroup_BlockQuoteIndication = -1;
        WebHTMLPDFGroup_ExpandAbbreviations = -1;
        WebHTMLPDFGroup_ExpandAcronyms = -1;
        WebHTMLPDFGroup_SpeakAccessKeysWithinWebPages = false;
        WebHTMLPDFGroup_CustomPlaceMarkerPageSummary = false;
        WebHTMLPDFGroup_TextBlockLenght = -1;
        WebHTMLPDFGroup_LinesPerDefault = -1;
        WebHTMLPDFGroup_MaxLineLength = -1;
        WebHTMLPDFGroup_DocumentPresentationMode = -1;
        WebHTMLPDFGroup_ScreenFollowVCursor = -1;
        WebHTMLPDFGroup_WrapNavigation = -1;
        WebHTMLPDFGroup_IncludeGraphics = -1;
        WebHTMLPDFGroup_GraphicRenderingOption = -1;
        WebHTMLPDFGroup_GraphicalLinkLastResort = -1;
        WebHTMLPDFGroup_FilterConsecutiveDuplicateLinks = -1;
        WebHTMLPDFGroup_IdentifyLinkType = -1;
        WebHTMLPDFGroup_IdentifySamePageLink = -1;
        WebHTMLPDFGroup_LinkText = -1;
        WebHTMLPDFGroup_IncludeGraphicLinks = -1;
        WebHTMLPDFGroup_IncludeImageMapLinks = -1;
        WebHTMLPDFGroup_HeadingIndication = -1;
        WebHTMLPDFGroup_FrameIndication = -1;
        WebHTMLPDFGroup_IgnoreInlineFrames = -1;
        WebHTMLPDFGroup_ListIndication = -1;
        WebHTMLPDFGroup_IndicateColSpan = -1;
        WebHTMLPDFGroup_TableDetection = -1;
        WebHTMLPDFGroup_EmbeddedActiveXSupport = -1;
        WebHTMLPDFGroup_FormFieldPromptOptions = -1;
        WebHTMLPDFGroup_PageRefreshFilter = -1;
        WebHTMLPDFGroup_IndicateElementAttribute = -1;
        WebHTMLPDFGroup_ButtonTextOptions = -1;
        FormModeGroup_AutoFormsMode = -1;
        FormModeGroup_AtuFormsModeThreshold = -1;
        FormModeGroup_FormsModeAutoOff = -1;
        FormModeGroup_IndicateFormsModeWithSounds = -1;
        FormModeGroup_EnterFormsModeSound = "";
        FormModeGroup_ExitFormsModeSound = "";
        TextProcessingGroup_FilterRepeatedCharacters = "";
        TextProcessingGroup_IndicateCapitalization = "";
        TextProcessingGroup_ListItem = "";
        TextProcessingGroup_SpellAlphanumericData = "";
        TextProcessingGroup_IndicateNewLinesWhen = "";
        TextProcessingGroup_IndicateTables = false;
        TextProcessingGroup_SmartWordReading = false;
        TextProcessingGroup_MixedCaseProcessing = false;
        TextProcessingGroup_UseDictionary = false;
        TextProcessingGroup_SpellPhoneticallyAlways = false;
        TextProcessingGroup_DetectLanguages = false;
        TextProcessingGroup_GeneralizedDialect = false;
        TextProcessingGroup_EnhancedEditSupport = false;
        TextProcessingGroup_SayItemStateFirst = false;
        TextProcessingGroup_SayWindowTypeFirst = false;
        TextProcessingGroup_SayWindowStateFirst = false;
        TextProcessingGroup_SayIndentedCharacters = false;
        TextProcessingGroup_NumericDateProcessing = "";
        TextProcessingGroup_NumberProcessing = "";
        TextProcessingGroup_SpeakSingleDigitsIfNumberContains = "";
        TextProcessingGroup_IfNumberContainsDashes = false;
        TextProcessingGroup_SpeakDollars = false;
        OtherSettings_VerbosityLevel = "";
        OtherSettings_SpeechAndSoundSchemes = "";
        OtherSettings_SayAll = "";
        OtherSettings_GraphicsAndSymbols = "";
        OtherSettings_Braille = "";
        OtherSettings_CaretAndCursor = "";
        OtherSettings_Synthesizer = "";
        OtherSettings_Punctuation = "";
        OtherSettings_VoiceAliases = "";
        OtherSettings_WindowClasses = "";
        OtherSettings_Keyboard = "";
        OtherSettings_CustomHighlight = "";
        
        
        
        OtherSettings_ResearchIt = "";
        OtherSettings_TextAnalyzer = "";
        OtherSettings_ConventientOCR = "";
        OtherSettings_Miscellaneous_SleppMode = "";
        OtherSettings_Miscellaneous_SearchForPrompts = false;
        OtherSettings_Miscellaneous_UseVirtualRibbonMenu = false;
        OtherSettings_Miscellaneous_UseVirtualPCCursor = false;
        OtherSettings_Miscellaneous_TrackFocusRectanle = "";
        OtherSettings_Miscellaneous_TextOutDelay = -1;
        OtherSettings_Miscellaneous_PixelsPerSpace = -1;
        OtherSettings_Miscellaneous_PixelsPerTab = -1;
        OtherSettings_Miscellaneous_LeftMargin = -1;
        OtherSettings_Miscellaneous_UnderlineProximity = false;
        OtherSettings_Miscellaneous_ClickTolerance = false;
        OtherSettings_Miscellaneous_RelyOnMSAA = false;
        OtherSettings_Miscellaneous_MSAAMode = "";
        PlaceMarkers = "";
        ConfigurationFilesExtension = "";
        DictionaryFilesExtension = "";
        GraphicLabelFilesExtension = "";
        KeyboardMappingFilesExtension = "";
        BrailleSettingFilesExtension = "";
        SpeechAndSoundSchemeFilesExtension = "";
        ScriptSourceFilesExtension = "";
        ScriptCompiledSourceFilesExtension = "";
        ScriptMessagesFilesExtension = "";
        ScriptHelpFiles = "";
    }
    
    @Override
    public String toString()
    {
        String result = "JAWSSettings";//OntologyManager.getInstance().classNamesAndIDs.get(OntologyManager.ScreenReaderSettings_JAWSSettings_ID);

        result = result + "\n\tSharedFiles: " + SharedFiles;
        result = result + "\n\tUserFiles: " + UserFiles;
        result = result + "\n\tUserOptionsGroup_ProgressBarUpdateInterval: " + Integer.toString(UserOptionsGroup_ProgressBarUpdateInterval);
        result = result + "\n\tUserOptionsGroup_TypingEcho: " + Integer.toString(UserOptionsGroup_TypingEcho);
        result = result + "\n\tUserOptionsGroup_bVirtViewer: " + Integer.toString(UserOptionsGroup_bVirtViewer);
        result = result + "\n\tUserOptionsGroup_ScreenEcho: " + Integer.toString(UserOptionsGroup_ScreenEcho);
        result = result + "\n\tUserOptionsGroup_TypingInterrupt: " + Integer.toString(UserOptionsGroup_TypingInterrupt);
        result = result + "\n\tUserOptionsGroup_InsertKeyMode: " + UserOptionsGroup_InsertKeyMode;
        result = result + "\n\tUserOptionsGroup_KeyRepeat: " + Integer.toString(UserOptionsGroup_KeyRepeat);
        result = result + "\n\tWebHTMLPDFGroup_SkipPastRepeatedText: " + Integer.toString(WebHTMLPDFGroup_SkipPastRepeatedText);
        result = result + "\n\tWebHTMLPDFGroup_BlockQuoteIndication: " + Integer.toString(WebHTMLPDFGroup_BlockQuoteIndication);
        result = result + "\n\tWebHTMLPDFGroup_ExpandAbbreviations: " + Integer.toString(WebHTMLPDFGroup_ExpandAbbreviations);
        result = result + "\n\tWebHTMLPDFGroup_ExpandAcronyms: " + Integer.toString(WebHTMLPDFGroup_ExpandAcronyms);
        result = result + "\n\tWebHTMLPDFGroup_SpeakAccessKeysWithinWebPages: " + Boolean.toString(WebHTMLPDFGroup_SpeakAccessKeysWithinWebPages);
        result = result + "\n\tWebHTMLPDFGroup_CustomPlaceMarkerPageSummary: " + Boolean.toString(WebHTMLPDFGroup_CustomPlaceMarkerPageSummary);
        result = result + "\n\tWebHTMLPDFGroup_TextBlockLenght: " + Integer.toString(WebHTMLPDFGroup_TextBlockLenght);
        result = result + "\n\tWebHTMLPDFGroup_LinesPerDefault: " + Integer.toString(WebHTMLPDFGroup_LinesPerDefault);
        result = result + "\n\tWebHTMLPDFGroup_MaxLineLength: " + Integer.toString(WebHTMLPDFGroup_MaxLineLength);
        result = result + "\n\tWebHTMLPDFGroup_DocumentPresentationMode: " + Integer.toString(WebHTMLPDFGroup_DocumentPresentationMode);
        result = result + "\n\tWebHTMLPDFGroup_ScreenFollowVCursor: " + Integer.toString(WebHTMLPDFGroup_ScreenFollowVCursor);
        result = result + "\n\tWebHTMLPDFGroup_WrapNavigation: " + Integer.toString(WebHTMLPDFGroup_WrapNavigation);
        result = result + "\n\tWebHTMLPDFGroup_IncludeGraphics: " + Integer.toString(WebHTMLPDFGroup_IncludeGraphics);
        result = result + "\n\tWebHTMLPDFGroup_GraphicRenderingOption: " + Integer.toString(WebHTMLPDFGroup_GraphicRenderingOption);
        result = result + "\n\tWebHTMLPDFGroup_GraphicalLinkLastResort: " + Integer.toString(WebHTMLPDFGroup_GraphicalLinkLastResort);
        result = result + "\n\tWebHTMLPDFGroup_FilterConsecutiveDuplicateLinks: " + Integer.toString(WebHTMLPDFGroup_FilterConsecutiveDuplicateLinks);
        result = result + "\n\tWebHTMLPDFGroup_IdentifyLinkType: " + Integer.toString(WebHTMLPDFGroup_IdentifyLinkType);
        result = result + "\n\tWebHTMLPDFGroup_IdentifySamePageLink: " + Integer.toString(WebHTMLPDFGroup_IdentifySamePageLink);
        result = result + "\n\tWebHTMLPDFGroup_LinkText: " + Integer.toString(WebHTMLPDFGroup_LinkText);
        result = result + "\n\tWebHTMLPDFGroup_IncludeGraphicLinks: " + Integer.toString(WebHTMLPDFGroup_IncludeGraphicLinks);
        result = result + "\n\tWebHTMLPDFGroup_IncludeImageMapLinks: " + Integer.toString(WebHTMLPDFGroup_IncludeImageMapLinks);
        result = result + "\n\tWebHTMLPDFGroup_HeadingIndication: " + Integer.toString(WebHTMLPDFGroup_HeadingIndication);
        result = result + "\n\tWebHTMLPDFGroup_FrameIndication: " + Integer.toString(WebHTMLPDFGroup_FrameIndication);
        result = result + "\n\tWebHTMLPDFGroup_IgnoreInlineFrames: " + Integer.toString(WebHTMLPDFGroup_IgnoreInlineFrames);
        result = result + "\n\tWebHTMLPDFGroup_ListIndication: " + Integer.toString(WebHTMLPDFGroup_ListIndication);
        result = result + "\n\tWebHTMLPDFGroup_IndicateColSpan: " + Integer.toString(WebHTMLPDFGroup_IndicateColSpan);
        result = result + "\n\tWebHTMLPDFGroup_TableDetection: " + Integer.toString(WebHTMLPDFGroup_TableDetection);
        result = result + "\n\tWebHTMLPDFGroup_EmbeddedActiveXSupport: " + Integer.toString(WebHTMLPDFGroup_EmbeddedActiveXSupport);
        result = result + "\n\tWebHTMLPDFGroup_FormFieldPromptOptions: " + Integer.toString(WebHTMLPDFGroup_FormFieldPromptOptions);
        result = result + "\n\tWebHTMLPDFGroup_PageRefreshFilter: " + Integer.toString(WebHTMLPDFGroup_PageRefreshFilter);
        result = result + "\n\tWebHTMLPDFGroup_IndicateElementAttribute: " + Integer.toString(WebHTMLPDFGroup_IndicateElementAttribute);
        result = result + "\n\tWebHTMLPDFGroup_ButtonTextOptions: " + Integer.toString(WebHTMLPDFGroup_ButtonTextOptions);
        result = result + "\n\tFormModeGroup_AutoFormsMode: " + Integer.toString(FormModeGroup_AutoFormsMode);
        result = result + "\n\tFormModeGroup_AtuFormsModeThreshold: " + Integer.toString(FormModeGroup_AtuFormsModeThreshold);
        result = result + "\n\tFormModeGroup_FormsModeAutoOff: " + Integer.toString(FormModeGroup_FormsModeAutoOff);
        result = result + "\n\tFormModeGroup_IndicateFormsModeWithSounds: " + Integer.toString(FormModeGroup_IndicateFormsModeWithSounds);
        result = result + "\n\tFormModeGroup_EnterFormsModeSound: " + FormModeGroup_EnterFormsModeSound;
        result = result + "\n\tFormModeGroup_ExitFormsModeSound: " + FormModeGroup_ExitFormsModeSound;
        result = result + "\n\tTextProcessingGroup_FilterRepeatedCharacters: " + TextProcessingGroup_FilterRepeatedCharacters;
        result = result + "\n\tTextProcessingGroup_IndicateCapitalization: " + TextProcessingGroup_IndicateCapitalization;
        result = result + "\n\tTextProcessingGroup_ListItem: " + TextProcessingGroup_ListItem;
        result = result + "\n\tTextProcessingGroup_SpellAlphanumericData: " + TextProcessingGroup_SpellAlphanumericData;
        result = result + "\n\tTextProcessingGroup_IndicateNewLinesWhen: " + TextProcessingGroup_IndicateNewLinesWhen;
        result = result + "\n\tTextProcessingGroup_IndicateTables: " + Boolean.toString(TextProcessingGroup_IndicateTables);
        result = result + "\n\tTextProcessingGroup_SmartWordReading: " + Boolean.toString(TextProcessingGroup_SmartWordReading);
        result = result + "\n\tTextProcessingGroup_MixedCaseProcessing: " + Boolean.toString(TextProcessingGroup_MixedCaseProcessing);
        result = result + "\n\tTextProcessingGroup_UseDictionary: " + Boolean.toString(TextProcessingGroup_UseDictionary);
        result = result + "\n\tTextProcessingGroup_SpellPhoneticallyAlways: " + Boolean.toString(TextProcessingGroup_SpellPhoneticallyAlways);
        result = result + "\n\tTextProcessingGroup_DetectLanguages: " + Boolean.toString(TextProcessingGroup_DetectLanguages);
        result = result + "\n\tTextProcessingGroup_GeneralizedDialect: " + Boolean.toString(TextProcessingGroup_GeneralizedDialect);
        result = result + "\n\tTextProcessingGroup_EnhancedEditSupport: " + Boolean.toString(TextProcessingGroup_EnhancedEditSupport);
        result = result + "\n\tTextProcessingGroup_SayItemStateFirst: " + Boolean.toString(TextProcessingGroup_SayItemStateFirst);
        result = result + "\n\tTextProcessingGroup_SayWindowTypeFirst: " + Boolean.toString(TextProcessingGroup_SayWindowTypeFirst);
        result = result + "\n\tTextProcessingGroup_SayWindowStateFirst: " + Boolean.toString(TextProcessingGroup_SayWindowStateFirst);
        result = result + "\n\tTextProcessingGroup_SayIndentedCharacters: " + Boolean.toString(TextProcessingGroup_SayIndentedCharacters);
        result = result + "\n\tTextProcessingGroup_NumericDateProcessing: " + TextProcessingGroup_NumericDateProcessing;
        result = result + "\n\tTextProcessingGroup_NumberProcessing: " + TextProcessingGroup_NumberProcessing;
        result = result + "\n\tTextProcessingGroup_SpeakSingleDigitsIfNumberContains: " + TextProcessingGroup_SpeakSingleDigitsIfNumberContains;
        result = result + "\n\tTextProcessingGroup_IfNumberContainsDashes: " + Boolean.toString(TextProcessingGroup_IfNumberContainsDashes);
        result = result + "\n\tTextProcessingGroup_SpeakDollars: " + Boolean.toString(TextProcessingGroup_SpeakDollars);
        result = result + "\n\tOtherSettings_VerbosityLevel: " + OtherSettings_VerbosityLevel;
        result = result + "\n\tOtherSettings_SpeechAndSoundSchemes: " + OtherSettings_SpeechAndSoundSchemes;
        result = result + "\n\tOtherSettings_SayAll: " + OtherSettings_SayAll;
        result = result + "\n\tOtherSettings_GraphicsAndSymbols: " + OtherSettings_GraphicsAndSymbols;
        result = result + "\n\tOtherSettings_Braille: " + OtherSettings_Braille;
        result = result + "\n\tOtherSettings_CaretAndCursor: " + OtherSettings_CaretAndCursor;
        result = result + "\n\tOtherSettings_Synthesizer: " + OtherSettings_Synthesizer;
        result = result + "\n\tOtherSettings_Punctuation: " + OtherSettings_Punctuation;
        result = result + "\n\tOtherSettings_VoiceAliases: " + OtherSettings_VoiceAliases;
        result = result + "\n\tOtherSettings_WindowClasses: " + OtherSettings_WindowClasses;
        result = result + "\n\tOtherSettings_Keyboard: " + OtherSettings_Keyboard;
        result = result + "\n\tOtherSettings_CustomHighlight: " + OtherSettings_CustomHighlight;
        
        result = result + "\n\tOtherSettings_ResearchIt: " + OtherSettings_ResearchIt;
        result = result + "\n\tOtherSettings_TextAnalyzer: " + OtherSettings_TextAnalyzer;
        result = result + "\n\tOtherSettings_ConventientOCR: " + OtherSettings_ConventientOCR;
        result = result + "\n\tOtherSettings_Miscellaneous_SleppMode: " + OtherSettings_Miscellaneous_SleppMode;
        result = result + "\n\tOtherSettings_Miscellaneous_SearchForPrompts: " + Boolean.toString(OtherSettings_Miscellaneous_SearchForPrompts);
        result = result + "\n\tOtherSettings_Miscellaneous_UseVirtualRibbonMenu: " + Boolean.toString(OtherSettings_Miscellaneous_UseVirtualRibbonMenu);
        result = result + "\n\tOtherSettings_Miscellaneous_UseVirtualPCCursor: " + Boolean.toString(OtherSettings_Miscellaneous_UseVirtualPCCursor);
        result = result + "\n\tOtherSettings_Miscellaneous_TrackFocusRectanle: " + OtherSettings_Miscellaneous_TrackFocusRectanle;
        result = result + "\n\tOtherSettings_Miscellaneous_TextOutDelay: " + Integer.toString(OtherSettings_Miscellaneous_TextOutDelay);
        result = result + "\n\tOtherSettings_Miscellaneous_PixelsPerSpace: " + Integer.toString(OtherSettings_Miscellaneous_PixelsPerSpace);
        result = result + "\n\tOtherSettings_Miscellaneous_PixelsPerTab: " + Integer.toString(OtherSettings_Miscellaneous_PixelsPerTab);
        result = result + "\n\tOtherSettings_Miscellaneous_LeftMargin: " + Integer.toString(OtherSettings_Miscellaneous_LeftMargin);
        result = result + "\n\tOtherSettings_Miscellaneous_UnderlineProximity: " + Boolean.toString(OtherSettings_Miscellaneous_UnderlineProximity);
        result = result + "\n\tOtherSettings_Miscellaneous_ClickTolerance: " + Boolean.toString(OtherSettings_Miscellaneous_ClickTolerance);
        result = result + "\n\tOtherSettings_Miscellaneous_RelyOnMSAA: " + Boolean.toString(OtherSettings_Miscellaneous_RelyOnMSAA);
        result = result + "\n\tOtherSettings_Miscellaneous_MSAAMode: " + OtherSettings_Miscellaneous_MSAAMode;
        result = result + "\n\tPlaceMarkers: " + PlaceMarkers;
        result = result + "\n\tConfigurationFilesExtension: " + ConfigurationFilesExtension;
        result = result + "\n\tDictionaryFilesExtension: " + DictionaryFilesExtension;
        result = result + "\n\tGraphicLabelFilesExtension: " + GraphicLabelFilesExtension;
        result = result + "\n\tKeyboardMappingFilesExtension: " + KeyboardMappingFilesExtension;
        result = result + "\n\tBrailleSettingFilesExtension: " + BrailleSettingFilesExtension;
        result = result + "\n\tSpeechAndSoundSchemeFilesExtension: " + SpeechAndSoundSchemeFilesExtension;
        result = result + "\n\tScriptSourceFilesExtension: " + ScriptSourceFilesExtension;
        result = result + "\n\tScriptCompiledSourceFilesExtension: " + ScriptCompiledSourceFilesExtension;
        result = result + "\n\tScriptMessagesFilesExtension: " + ScriptMessagesFilesExtension;
        result = result + "\n\tScriptHelpFiles: " + ScriptHelpFiles;        

        return result;
    }
    
    public String toJSON()
    {
        String result = "";
        Gson tmpGson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        result = tmpGson.toJson(this);
        return result;
    }

}
