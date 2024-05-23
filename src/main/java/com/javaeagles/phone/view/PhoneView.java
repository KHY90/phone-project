package com.javaeagles.phone.view;

import com.javaeagles.phone.controller.PhoneController;

import java.util.Scanner;

import static com.javaeagles.phone.controller.PhoneController.*;

public class PhoneView {

    private static boolean state = true;
    private static PhoneController phoneController = new PhoneController();

    public static void run(){

        while (state){

            System.out.println("화면 번호를 입력해주세요 : ");
            System.out.println("1. 화면 전체보기");
            System.out.println("2. 이름으로 조회하기 ");
            System.out.println("3. 정보 등록하기");
            System.out.println("4. 정보 수정하기");
            System.out.println("5. 정보 삭제하기");

            Scanner sc = new Scanner(System.in);

            int index = Integer.parseInt(sc.nextLine());

            switch (index){
                case 1 :
                    phoneViewAll();
                    break;
                case 2 :
                    phoneFindByName();
                    break;
                case 3:
                    phoneInsert();
                    break;
                case 4:
                    phoneUpdate();
                    break;
                case 5:
                    phoneDelete();
                    break;
            }

            // System.out.print("종료를 하시겠습니까? (yes Or no) : ");
            // String result = sc.nextLine();
            // // equalsIgnoreCase : 대소문자 둘 다 읽어준다.
            // if(result.equalsIgnoreCase("yes")){
            //     state = false;
            //     sc.close();
            // } else if (result.equalsIgnoreCase("no")) {
            //     state = true;
            // }else {
            //     System.out.println("잘못 입력하셨습니다");
            //     state = false;
            //     sc.close();
            // }
        }

    }
}
