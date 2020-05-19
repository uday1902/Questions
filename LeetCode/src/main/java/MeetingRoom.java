package src.main.java;

import java.util.*;

public class MeetingRoom {
    static int minMeetingRooms(List<List<Integer>> meetingRooms) {
        List<Integer> times = new ArrayList<>();
        Map<Integer, Integer> mp = new HashMap<>();
        for(List<Integer> meeting : meetingRooms) {
            insert(meeting.get(0),1, mp);
            insert(meeting.get(1),-1, mp);
            times.add(meeting.get(0));
            times.add(meeting.get(1));
        }
        Collections.sort(times);
        int noMeetingRooms = 0;
        int globalMeetingRooms = Integer.MIN_VALUE;
        for(int time : times) {
            noMeetingRooms += mp.get(time);
            if(noMeetingRooms > globalMeetingRooms) {
                globalMeetingRooms = noMeetingRooms;
            }
        }
        return globalMeetingRooms;
    }

    private static void insert(int ele, int inc , Map<Integer, Integer> mp){
        if(mp.containsKey(ele)){
            mp.put(ele, mp.get(ele) + inc);
        }
        mp.put(ele,inc);
    }

    public static void main(String[] args) {
        System.out.println("Hello, world!");
        List<List<Integer>> meetingRooms = new ArrayList<>();
        meetingRooms.add(createMeetingRoom(4,8));
        meetingRooms.add(createMeetingRoom(3,6));
        meetingRooms.add(createMeetingRoom(12,14));
        meetingRooms.add(createMeetingRoom(5,12));
        meetingRooms.add(createMeetingRoom(9,10));
        System.out.println((minMeetingRooms(meetingRooms)));

    }

    private static List<Integer> createMeetingRoom(int startTime, int endTime) {
        List<Integer> meetingRoom = new ArrayList<>();
        meetingRoom.add(startTime);
        meetingRoom.add(endTime);
        return meetingRoom;
    }
}