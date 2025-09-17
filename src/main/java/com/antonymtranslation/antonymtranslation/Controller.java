package com.antonymtranslation.antonymtranslation;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class Controller {
    @FXML
    private Text text;
    @FXML
    private TextField input_box;
    @FXML
    private Button copy_button;
    @FXML
    private Button run_button;


    @FXML
    private void initialize(){
        // 创建自定义插值器 - 弹性效果
        Interpolator elasticInterpolator = new Interpolator() {
            @Override
            protected double curve(double t) {
                // 弹性函数: 实现弹性效果
                return Math.pow(2, -10 * t) * Math.sin((t - 0.1) * (2 * Math.PI) / 0.4) + 1;
            }
        };

        //设置动画
        TranslateTransition show_animation = new TranslateTransition(Duration.seconds(2), input_box);
        show_animation.setInterpolator(elasticInterpolator);
        show_animation.setFromY(150);
        show_animation.setToY(20);
        show_animation.setAutoReverse(true);
        show_animation.setCycleCount(1);

        //设置事件
        input_box.setOnAction(_ -> {
            Translation(input_box.getText());
        });
        run_button.setOnAction(_ -> {
            Translation(input_box.getText());
        });
        copy_button.setOnAction(_ -> {
            // 要复制的文本内容
            String textToCopy = text.getText();

            // 执行复制操作
            boolean success = copyToClipboard(textToCopy);

            if (success) {
                System.out.println("文本已成功复制到剪贴板！");
            } else {
                System.out.println("复制到剪贴板失败！");
            }
        });

        show_animation.play();
    }

    /**
     * 将文本复制到系统剪贴板
     * @param text 要复制的文本
     * @return 是否成功
     */
    public static boolean copyToClipboard(String text) {
        try {
            // 获取系统剪贴板
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

            // 创建StringSelection对象
            StringSelection selection = new StringSelection(text);

            // 设置剪贴板内容
            clipboard.setContents(selection, null);

            return true;
        } catch (Exception e) {
            System.err.println("复制到剪贴板时出错: " + e.getMessage());
            return false;
        }
    }

    /**
     * 翻译文本
     * @param word
     */
    private void Translation(String word){
        StringBuilder builder = new StringBuilder();
        for (char text_char : word.toCharArray()){
            switch (text_char){
                case '坏' : builder.append("好");break;
                case '好' : builder.append("坏");break;
                case '多' : builder.append("少");break;
                case '少' : builder.append("多");break;
                case '上' : builder.append("下");break;
                case '下' : builder.append("上");break;
                case '公' : builder.append('母');break;
                case '母', '私': builder.append('公');break;
                case '雌' : builder.append('雄');break;
                case '雄' : builder.append('雌');break;
                case '里' : builder.append('外');break;
                case '外' : builder.append('里');break;
                case '大' : builder.append('小');break;
                case '小' : builder.append('大');break;
                case '开' : builder.append('关');break;
                case '关' : builder.append('开');break;
                case '你' : builder.append('我');break;
                case '我' : builder.append('你');break;
                case '男' : builder.append('女');break;
                case '女' : builder.append('男');break;
                case '不' : builder.append('是');break;
                case '是' : builder.append('不');break;
                case '进' : builder.append('出');break;
                case '出' : builder.append('进');break;
                case '正' : builder.append('反');break;
                case '反' : builder.append('正');break;
                case '轻' : builder.append('重');break;
                case '重' : builder.append('轻');break;
                case '高' : builder.append('矮');break;
                case '矮' : builder.append('高');break;
                case '长' : builder.append('短');break;
                case '短' : builder.append('长');break;
                case '断' : builder.append('连');break;
                case '连' : builder.append('断');break;
                case '净' : builder.append('脏');break;
                case '脏' : builder.append('净');break;
                case '干' : builder.append('湿');break;
                case '湿' : builder.append('干');break;
                case '近' : builder.append('远');break;
                case '远' : builder.append('进');break;
                case '帅' : builder.append('丑');break;
                case '丑' : builder.append('帅');break;
                case '哥' : builder.append('弟');break;
                case '弟' : builder.append('哥');break;
                case '姐' : builder.append('妹');break;
                case '妹' : builder.append('姐');break;
                case '站' : builder.append('坐');break;
                case '坐' : builder.append('站');break;
                case '真' : builder.append('假');break;
                case '假' : builder.append('真');break;
                case '老' : builder.append('新');break;
                case '新' : builder.append('老');break;

                default : builder.append(text_char);
            }
            
            Timeline timeline_animation = new Timeline();
            for (int i = 0; i < builder.toString().length()+1; i++) {
                final int index = i;
                KeyFrame keyFrame = new KeyFrame(
                        Duration.millis(8).multiply(i*5),
                        event -> text.setText(builder.substring(0, index))
                );
                timeline_animation.getKeyFrames().add(keyFrame);
            }

            timeline_animation.play();
        }
    }
}