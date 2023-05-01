/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.services;

/**
 *
 * @author Ison Ho
 */
// File Name SendEmail.java
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService {

    public static void main(String[] args) throws jakarta.mail.MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "eplnoreply12@gmail.com";
        String password = "rianmbyvduukiyuq";

        String mailTo = "ckyllesk@gmail.com";
        String subject = "mail content";
        String content = Email.build("NoobyDongo", "Hi", "10 AUG", "6pm-10pm<br>Happy Apple Farm");

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);

        jakarta.mail.internet.MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setTo(mailTo);
        helper.setSubject(subject);
        helper.setText(content, true);

        sender.send(message);
    }

    class Email {

        public String body = "";
        public String head = "";
        public String content = "";

        public Email() {

        }

        public static String build(String name, String message, String date, String timeLoc) {
            
            return beforeName + name + beforeMessage + message + beforeDate + date + beforeTimeLoc + timeLoc + afterTimeLoc;
        }
    
        private static final String afterTimeLoc = "</p></td> </tr> </table></td> </tr> </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:240px\" valign=\"top\"><![endif]--> <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;width:240px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"es-m-txt-l\" align=\"right\" style=\"padding:0;Margin:0;padding-top:10px;padding-bottom:10px\"><!--[if mso]><a href=\"https://viewstripo.email\" target=\"_blank\" hidden> <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email\" style=\"height:51px; v-text-anchor:middle; width:222px\" arcsize=\"0%\" stroke=\"f\" fillcolor=\"#726ae3\"> <w:anchorlock></w:anchorlock> <center style='color:#ffffff; font-family:lato, \"helvetica neue\", helvetica, arial, sans-serif; font-size:18px; font-weight:700; line-height:18px; mso-text-raise:1px'>Chect out the venue</center> </v:roundrect></a> <![endif]--><!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"border-style:solid;border-color:#2CB543;background:#726ae3;border-width:0px;display:inline-block;border-radius:0px;width:auto;mso-border-alt:10px;mso-hide:all\"><a href=\"https://viewstripo.email\" class=\"es-button es-button-1\" target=\"_blank\" style=\"mso-style-priority:100 !important;text-decoration:none;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;color:#ffffff;font-size:18px;padding:15px 30px;display:inline-block;background:#726ae3;border-radius:0px;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-weight:bold;font-style:normal;line-height:22px;width:auto;text-align:center;border-color:#726ae3\">Chect out the venue</a></span><!--<![endif]--></td> </tr> </table></td> </tr> </table><!--[if mso]></td></tr></table><![endif]--></td> </tr> </table></td> </tr> </table> <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> <tr> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#121222;width:650px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#121222\" align=\"center\"> <tr> <td align=\"left\" style=\"Margin:0;padding-left:20px;padding-right:20px;padding-top:30px;padding-bottom:30px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:610px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"es-infoblock\" align=\"center\" style=\"padding:0;Margin:0;line-height:14px;font-size:12px;color:#CCCCCC\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:17px;color:#efefef;font-size:14px\">You are receiving this email because you are invited by one of our memebrs to a upcoming event. Make sure our messages get to your Inbox (and not your bulk or junk folders).<br><a target=\"_blank\" href=\"https://viewstripo.email/\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#ffffff;font-size:14px\">Privacy police</a>&nbsp;|&nbsp;<a target=\"_blank\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#ffffff;font-size:14px\" href=\"\">Unsubscribe</a></p></td> </tr> </table></td> </tr> </table></td> </tr> </table></td> </tr> </table> <table class=\"es-footer\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> <tr> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-footer-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#121222;width:650px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#121222\" align=\"center\"> <tr> <td align=\"left\" style=\"Margin:0;padding-top:20px;padding-left:20px;padding-right:20px;padding-bottom:40px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;width:610px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"es-infoblock made_with\" style=\"padding:0;Margin:0;line-height:120%;font-size:0;color:#CCCCCC\" align=\"center\"><a target=\"_blank\" href=\"https://viewstripo.email/?utm_source=templates&utm_medium=email&utm_campaign=gadget_17&utm_content=upcoming_stand_up_meeting\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#CCCCCC;font-size:12px\"><img src=\"https://dyrueh.stripocdn.email/content/guids/CABINET_09023af45624943febfa123c229a060b/images/7911561025989373.png\" alt style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" width=\"125\" height=\"56\"></a></td> </tr> </table></td> </tr> </table></td> </tr> </table></td> </tr> </table></td> </tr> </table> </div> </body> </html>";
                //requires <br>
        private static final String beforeTimeLoc = "</h1></td> </tr> </table></td> <td class=\"es-hidden\" style=\"padding:0;Margin:0;width:20px\"></td> </tr> </table><!--[if mso]></td><td style=\"width:250px\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> <tr> <td class=\"es-m-p20b\" align=\"left\" style=\"padding:0;Margin:0;width:250px\"> <table style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;border-left:2px solid #726ae3\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\"> <tr> <td align=\"left\" style=\"padding:10px;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#ffffff;font-size:18px\">";
        private static final String beforeDate = "</p></td> </tr> </table></td> </tr> </table></td> </tr> </table></td> </tr> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> <tr> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#19182d;width:650px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#19182d\" align=\"center\"> <tr> <td class=\"esdev-adapt-off\" align=\"left\" style=\"Margin:0;padding-bottom:20px;padding-left:20px;padding-right:20px;padding-top:25px\"> <table class=\"esdev-mso-table\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;width:610px\"> <tr> <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\"> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> <tr> <td valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:30px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"esd-block-text\" style=\"padding-top:15px\" align=\"left\"> <div style=\"background-color: #726ae3;width:15px;height:15px;border-radius:50%\"><br></div> </td> </tr> </table></td> </tr> </table></td> <td style=\"padding:0;Margin:0;width:5px\"></td> <td class=\"esdev-mso-td\" valign=\"top\" style=\"padding:0;Margin:0\"> <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;width:575px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;padding-top:10px\"><h2 style=\"Margin:0;line-height:26px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:22px;font-style:normal;font-weight:bold;color:#dbe36a\">Upcoming Event</h2></td> </tr> </table></td> </tr> </table></td> </tr> </table></td> </tr> <tr> <td align=\"left\" style=\"Margin:0;padding-top:10px;padding-left:20px;padding-right:20px;padding-bottom:40px\"><!--[if mso]><table style=\"width:610px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:100px\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> <tr> <td class=\"es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:80px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"es-m-txt-l\" align=\"center\" style=\"padding:0;Margin:0\"><h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#dbe36a\">";
        private static final String beforeMessage = "</h1></td> </tr> <tr> <td align=\"left\" style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;line-height:27px;color:#efefef;font-size:18px\">";
        
        private static final String beforeName = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> <html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" style=\"font-family:lato, 'helvetica neue', helvetica, arial, sans-serif\"> <head> <meta charset=\"UTF-8\"> <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\"> <meta name=\"x-apple-disable-message-reformatting\"> <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"> <meta content=\"telephone=no\" name=\"format-detection\"> <title>New email template 2023-05-01</title><!--[if (mso 16)]> <style type=\"text/css\"> a {text-decoration: none;} </style> <![endif]--><!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]--><!--[if gte mso 9]> <xml> <o:OfficeDocumentSettings> <o:AllowPNG></o:AllowPNG> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml> <![endif]--><!--[if !mso]><!-- --> <link href=\"https://fonts.googleapis.com/css?family=Lato:400,400i,700,700i\" rel=\"stylesheet\"><!--<![endif]--> <style type=\"text/css\"> #outlook a { padding:0; } .es-button { mso-style-priority:100!important; text-decoration:none!important; } a[x-apple-data-detectors] { color:inherit!important; text-decoration:none!important; font-size:inherit!important; font-family:inherit!important; font-weight:inherit!important; line-height:inherit!important; } .es-desk-hidden { display:none; float:left; overflow:hidden; width:0; max-height:0; line-height:0; mso-hide:all; } [data-ogsb] .es-button.es-button-1 { padding:15px 30px!important; } @media only screen and (max-width:600px) {p, ul li, ol li, a { line-height:150%!important } h1, h2, h3, h1 a, h2 a, h3 a { line-height:120% } h1 { font-size:30px!important; text-align:left } h2 { font-size:24px!important; text-align:left } h3 { font-size:20px!important; text-align:left } .es-header-body h1 a, .es-content-body h1 a, .es-footer-body h1 a { font-size:30px!important; text-align:left } .es-header-body h2 a, .es-content-body h2 a, .es-footer-body h2 a { font-size:24px!important; text-align:left } .es-header-body h3 a, .es-content-body h3 a, .es-footer-body h3 a { font-size:20px!important; text-align:left } .es-menu td a { font-size:14px!important } .es-header-body p, .es-header-body ul li, .es-header-body ol li, .es-header-body a { font-size:14px!important } .es-content-body p, .es-content-body ul li, .es-content-body ol li, .es-content-body a { font-size:14px!important } .es-footer-body p, .es-footer-body ul li, .es-footer-body ol li, .es-footer-body a { font-size:14px!important } .es-infoblock p, .es-infoblock ul li, .es-infoblock ol li, .es-infoblock a { font-size:12px!important } *[class=\"gmail-fix\"] { display:none!important } .es-m-txt-c, .es-m-txt-c h1, .es-m-txt-c h2, .es-m-txt-c h3 { text-align:center!important } .es-m-txt-r, .es-m-txt-r h1, .es-m-txt-r h2, .es-m-txt-r h3 { text-align:right!important } .es-m-txt-l, .es-m-txt-l h1, .es-m-txt-l h2, .es-m-txt-l h3 { text-align:left!important } .es-m-txt-r img, .es-m-txt-c img, .es-m-txt-l img { display:inline!important } .es-button-border { display:inline-block!important } a.es-button, button.es-button { font-size:18px!important; display:inline-block!important } .es-adaptive table, .es-left, .es-right { width:100%!important } .es-content table, .es-header table, .es-footer table, .es-content, .es-footer, .es-header { width:100%!important; max-width:600px!important } .es-adapt-td { display:block!important; width:100%!important } .adapt-img { width:100%!important; height:auto!important } .es-m-p0 { padding:0!important } .es-m-p0r { padding-right:0!important } .es-m-p0l { padding-left:0!important } .es-m-p0t { padding-top:0!important } .es-m-p0b { padding-bottom:0!important } .es-m-p20b { padding-bottom:20px!important } .es-mobile-hidden, .es-hidden { display:none!important } tr.es-desk-hidden, td.es-desk-hidden, table.es-desk-hidden { width:auto!important; overflow:visible!important; float:none!important; max-height:inherit!important; line-height:inherit!important } tr.es-desk-hidden { display:table-row!important } table.es-desk-hidden { display:table!important } td.es-desk-menu-hidden { display:table-cell!important } .es-menu td { width:1%!important } table.es-table-not-adapt, .esd-block-html table { width:auto!important } table.es-social { display:inline-block!important } table.es-social td { display:inline-block!important } .es-m-p5 { padding:5px!important } .es-m-p5t { padding-top:5px!important } .es-m-p5b { padding-bottom:5px!important } .es-m-p5r { padding-right:5px!important } .es-m-p5l { padding-left:5px!important } .es-m-p10 { padding:10px!important } .es-m-p10t { padding-top:10px!important } .es-m-p10b { padding-bottom:10px!important } .es-m-p10r { padding-right:10px!important } .es-m-p10l { padding-left:10px!important } .es-m-p15 { padding:15px!important } .es-m-p15t { padding-top:15px!important } .es-m-p15b { padding-bottom:15px!important } .es-m-p15r { padding-right:15px!important } .es-m-p15l { padding-left:15px!important } .es-m-p20 { padding:20px!important } .es-m-p20t { padding-top:20px!important } .es-m-p20r { padding-right:20px!important } .es-m-p20l { padding-left:20px!important } .es-m-p25 { padding:25px!important } .es-m-p25t { padding-top:25px!important } .es-m-p25b { padding-bottom:25px!important } .es-m-p25r { padding-right:25px!important } .es-m-p25l { padding-left:25px!important } .es-m-p30 { padding:30px!important } .es-m-p30t { padding-top:30px!important } .es-m-p30b { padding-bottom:30px!important } .es-m-p30r { padding-right:30px!important } .es-m-p30l { padding-left:30px!important } .es-m-p35 { padding:35px!important } .es-m-p35t { padding-top:35px!important } .es-m-p35b { padding-bottom:35px!important } .es-m-p35r { padding-right:35px!important } .es-m-p35l { padding-left:35px!important } .es-m-p40 { padding:40px!important } .es-m-p40t { padding-top:40px!important } .es-m-p40b { padding-bottom:40px!important } .es-m-p40r { padding-right:40px!important } .es-m-p40l { padding-left:40px!important } .es-desk-hidden { display:table-row!important; width:auto!important; overflow:visible!important; max-height:inherit!important } .h-auto { height:auto!important } } </style> </head> <body style=\"width:100%;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;padding:0;Margin:0\"> <div class=\"es-wrapper-color\" style=\"background-color:#FFFFFF\"><!--[if gte mso 9]> <v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#ffffff\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill> </v:background> <![endif]--> <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;padding:0;Margin:0;width:100%;height:100%;background-repeat:repeat;background-position:center top;background-color:#FFFFFF\"> <tr> <td valign=\"top\" style=\"padding:0;Margin:0\"> <table class=\"es-header\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%;background-color:transparent;background-repeat:repeat;background-position:center top\"> <tr> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-header-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1f1e39;width:650px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#1f1e39\" align=\"center\"> <tr> <td class=\"es-m-p0b\" align=\"left\" style=\"padding:20px;Margin:0\"><!--[if mso]><table style=\"width:610px\" cellpadding=\"0\" cellspacing=\"0\"><tr><td style=\"width:50px\" valign=\"top\"><![endif]--> <table class=\"es-left\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:left\"> <tr> <td class=\"es-m-p0r es-m-p20b\" valign=\"top\" align=\"center\" style=\"padding:0;Margin:0;width:50px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td style=\"padding:0;Margin:0;padding-top:20px;padding-bottom:20px;font-size:0px\" align=\"center\"><a target=\"_blank\" href=\"https://viewstripo.email\" style=\"-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;text-decoration:none;color:#2CB543;font-size:14px\"><img src=\"https://raw.githubusercontent.com/NoobyDongo/ITP4511_ass/main/logo.PNG\" alt=\"Logo\" style=\"display:block;border:0;outline:none;text-decoration:none;-ms-interpolation-mode:bicubic\" title=\"Logo\" width=\"50\"></a></td> </tr> </table></td> </tr> </table><!--[if mso]></td><td style=\"width:20px\"></td><td style=\"width:540px\" valign=\"top\"><![endif]--> <table class=\"es-right\" cellspacing=\"0\" cellpadding=\"0\" align=\"right\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;float:right\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;width:540px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr class=\"es-mobile-hidden\"> <td class=\"h-auto\" valign=\"middle\" height=\"85\" align=\"left\" style=\"padding:0;Margin:0\"><p style=\"Margin:0;-webkit-text-size-adjust:none;-ms-text-size-adjust:none;mso-line-height-rule:exactly;font-family:-apple-system, blinkmacsystemfont, 'segoe ui', roboto, helvetica, arial, sans-serif, 'apple color emoji', 'segoe ui emoji', 'segoe ui symbol';line-height:72px;color:#373671;font-size:48px;background:-webkit-linear-gradient(110deg, #6C75CA 30%, #A155A2 40%, #EE2667 85%);-webkit-background-clip:text;-webkit-text-fill-color:transparent\"><strong>Event Point Limited</strong></p></td> </tr> </table></td> </tr> </table><!--[if mso]></td></tr></table><![endif]--></td> </tr> </table></td> </tr> </table> <table class=\"es-content\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;table-layout:fixed !important;width:100%\"> <tr> <td align=\"center\" style=\"padding:0;Margin:0\"> <table class=\"es-content-body\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px;background-color:#1f1e39;width:650px\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#19182d\" align=\"center\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;padding-left:20px;padding-right:20px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td align=\"left\" style=\"padding:0;Margin:0;width:610px\"> <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\" style=\"mso-table-lspace:0pt;mso-table-rspace:0pt;border-collapse:collapse;border-spacing:0px\"> <tr> <td class=\"es-m-p0t\" align=\"left\" style=\"padding:0;Margin:0;padding-top:20px\"><h1 style=\"Margin:0;line-height:36px;mso-line-height-rule:exactly;font-family:lato, 'helvetica neue', helvetica, arial, sans-serif;font-size:30px;font-style:normal;font-weight:bold;color:#dbe36a\">";
    }
}
