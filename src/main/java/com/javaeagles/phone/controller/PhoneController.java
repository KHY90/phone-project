package com.javaeagles.phone.controller;

import com.javaeagles.phone.dto.PhoneDTO;
import com.javaeagles.phone.service.PhoneService;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneController {

    private static PhoneService phoneService;

    public PhoneController() {
        this.phoneService = new PhoneService();
    }

    public static void phoneViewAll() {
        // 현재 html의 화면을 암시하고 만든 것이다.
        // view는 사용자에게 데이터를 입력받고 서버에 전달하며, 결과를 사용자에게 보여주기 위한 용도로 사용된다.

        System.out.println(" 정보 전체 조회 ");

        try {
            ArrayList ph = phoneService.phoneViewAll();
            System.out.println(ph);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void phoneFindByName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요 : ");
        PhoneDTO ph = null; // 강제 초기화
        String name = sc.nextLine();

        try {
            ph = phoneService.phoneFindByName(name);

            if(name == null || name.trim().isEmpty()){
                System.out.println("공백입니다.");
            }else if(ph.getUserName() != null){
                System.out.println(ph);
            }else{
                System.out.println("등록된 이름이 없습니다. 다시 입력해주세요.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void phoneFindByName(){
//        Scanner sc = new Scanner(System.in);
//        System.out.print("이름을 입력하세요 : ");
//        String name = sc.nextLine();
//        PhoneDTO ph = null; // 강제 초기화
//        PhNameDTO phName = null;

//        try {
//            if( name.matches("[a-zA-Z0-9]+|[ㄱ-ㅎㅏ]+")){
//                System.out.println("잘못 입력하셧습니다.");
//            }else if(name != null && name.trim().isEmpty()){ // trim()은 문자열에서 앞뒤의 공백을 제거합니다.
//                System.out.println("공백입니다.");
//            }
//            else {
//                ph = phoneService.phoneFindByName(name);
//                System.out.println(ph);}
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            if (name.matches("[a-zA-Z]+")) { // 영어로 된 이름인 경우
//                ph = phoneService.phoneFindByName(name);
//                System.out.println(ph);
//            } else if (name.matches("[ㄱ-ㅎㅏ]+")) { // 한글 자모로 된 이름인 경우
//                System.out.println("잘못 입력하셧습니다.");
//            } else if (name != null && name.trim().isEmpty()) { // 공백인 경우
//                System.out.println("공백입니다.");
//            } else { // 그 외의 경우
//                System.out.println("잘못 입력하셨습니다.");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    public static void phoneInsert(){
        Scanner sc = new Scanner(System.in);
        PhoneDTO ph = new PhoneDTO();

        while (true) {
            System.out.println("등록할 전화번호의 정보를 입력해주세요 ");
            System.out.print("이름 : ");
            String userName = sc.nextLine();
            if (userName == null || userName.isEmpty()) {
                System.out.println("이름이 입력되지 않았습니다. 다시 입력해주세요.");
                continue;
            }
            ph.setUserName(userName);
            break;
        }
        System.out.print("이메일 : ");
        ph.setUserEmail(sc.nextLine());
        System.out.print("메모 : ");
        ph.setUserMemo(sc.nextLine());
        System.out.print("그룹 : ");
        ph.setUserGroup(sc.nextLine());

        while (true) {
            System.out.print("전화번호 : ");
            String phone = sc.nextLine();
            if (phone == null || phone.isEmpty()) {
                System.out.println("전화번호가 입력되지 않았습니다. 다시 입력해주세요.");
                continue;
            }
            ph.setPhone(phone);
            break;
        }
        System.out.print("전화번호 이름 : ");
        ph.setPhoneName(sc.nextLine());
        try {
            String result = phoneService.phoneInsert(ph);
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void phoneUpdate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 단축번호를 입력하세요");
        String index = sc.nextLine();
        PhoneDTO ph = phoneService.phoneFindById(index);

        if (ph == null) {
            System.out.println("변경할 번호가 존재하지 않습니다.");
            return;
        }
        System.out.println(ph);

        try {
            System.out.print("변경할 이름을 입력해주세요 : ");
            String name = sc.nextLine();

            if(name == null || name.trim().isEmpty()) {    // trim() :
                System.out.println("공백입니다.");
            }
            else{
                PhoneDTO modifyEmp = phoneService.phoneModify(name, index);
                System.out.println(modifyEmp);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void phoneDelete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 연락처의 이름을 입력하세요 : ");
        String name = sc.nextLine();

        PhoneDTO ph = null; // 강제 초기화

        try {
            ph = phoneService.phoneFindByName(name);
            if(name == null || name.trim().isEmpty()){    // trim() :
                System.out.println("공백입니다.");
            }
            else if(ph.getUserName() != null){
                System.out.println(ph);
                if(ph.getUserName() != null) {
                    System.out.print("삭제하시겠습니까? ( yes / no ) : ");
                    String check = sc.nextLine();
                    if (check.equalsIgnoreCase("yes")) {
                        try {
                            phoneService.phoneDelete(name);
                            System.out.println("삭제가 완료되었습니다.");
                        } catch (Exception e) {
                            System.out.println("1212");
                            throw new RuntimeException(e);
                        }
                    } else {
                        System.out.println("다시 시도해주세요.");
                    }
                }
            }else if (ph.getUserName() == null){
                System.out.println("등록된 이름이 없습니다. 다시 입력해주세요.");

            }
        } catch (Exception e) {
            System.out.println("12");
            throw new RuntimeException(e);

        }
    }
    
}
