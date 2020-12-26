package homework;
import java.util.ArrayList;
import java.util.List;

public class test6 {
    public static void main(String[] args) {
        User zhangSan = new User("ZhangSan");
        User liSi = new User("LiSi");
        User wangWu = new User("WangWu");
        User zhaoLiu = new User("zhaoliu");
        FollowButton[] followButtons = new FollowButton[4];
        followButtons[0] = new FollowButton(zhangSan);
        followButtons[1] = new FollowButton(liSi);
        followButtons[2] = new FollowButton(wangWu);
        followButtons[3] = new FollowButton(zhaoLiu);
        FollowIncrease followIncrease = new FollowIncrease();
        FansIncrease fansIncrease = new FansIncrease();
        BeFollowed beFollowed = new BeFollowed();
        for(int i = 0;i < 4;i++){
            followButtons[i].addObserver(followIncrease);
            followButtons[i].addObserver(fansIncrease);
            followButtons[i].addObserver(beFollowed);
        }
        followButtons[0].click(liSi);
        followButtons[0].click(wangWu);
        followButtons[1].click(zhangSan);
        followButtons[1].click(wangWu);
        followButtons[1].click(zhaoLiu);
        followButtons[2].click(zhangSan);
        followButtons[2].click(liSi);
        followButtons[2].click(zhaoLiu);
        followButtons[3].click(zhangSan);
        followButtons[3].click(wangWu);
        zhangSan.showFanList();
        zhangSan.showFollowList();
        zhangSan.showMessageList();
        liSi.showFanList();;
        liSi.showFollowList();
        liSi.showMessageList();
        wangWu.showFanList();
        wangWu.showFollowList();
        wangWu.showMessageList();
        zhaoLiu.showFanList();
        zhaoLiu.showFollowList();
        zhaoLiu.showMessageList();
    }   
}

class User{
    protected String name;
    protected List<User> followList;
    protected List<User> fanList;
    protected List<String> messageList;
    public User(String _name){
        name = _name;
        followList = new ArrayList<>();
        fanList = new ArrayList<>();
        messageList = new ArrayList<>();
    }
    public String getName(){
        return name;
    }
    public List<User> getFollowList(){
        return followList;
    }
    public List<User> getFanList(){
        return fanList;
    }
    public void addMessage(String message){
        messageList.add(message);
    }
    public void addFollower(User follower){
        followList.add(follower);
    }
    public void addFans(User fans){
        fanList.add(fans);
    }
    public void showFollowList(){
        System.out.println(name + "'s follow list:");
        for(int i = 0;i < followList.size();i++){
            System.out.println(followList.get(i).getName());
        }
    }
    public void showFanList(){
        System.out.println(name + "'s fans list:");
        for(int i = 0;i < fanList.size();i++){
            System.out.println(fanList.get(i).getName());
        }
    }
    public void showMessageList(){
        System.out.println(name + "'s message list:");
        for(int i = 0;i < messageList.size();i++){
            System.out.println(messageList.get(i));
        }
    }
}
abstract class FollowButtons{
    public abstract void click(User clicker);
}
class FollowButton extends FollowButtons{
    protected User pageUser;
    protected List<Observer> observerList;
    public FollowButton(User _pageUser){
        pageUser = _pageUser;
        observerList = new ArrayList<>();
    }
    public void click(User clicker){
        for (Observer obs : observerList){
            ((Observer) obs).notify(pageUser,clicker);
        }
    }
    public void addObserver(Observer observer){
        observerList.add(observer);
    }
}

interface Observer{
    void notify(User pageUser,User follower);
}

class FollowIncrease implements Observer{
    public void notify(User pageUser,User follower){
        pageUser.addFollower(follower);
    }
}

class FansIncrease implements Observer{
    public void notify(User pageUser,User follower){
        follower.addFans(pageUser);
    }
}

class BeFollowed implements Observer{
    public void notify(User pageUser,User follower){
        String message = "You are followed by " + pageUser.getName();
        follower.addMessage(message);
    }
}





