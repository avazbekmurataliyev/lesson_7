    package uz.maktab.maktab.payload;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import uz.maktab.maktab.model.Groups;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class GroupPayload {
        // Teacher
        private String fullName ;
        private String fan ;
        private String phoneNumber ;

        // Student
        private String firstName ;
        private String lastName ;

        // Subject
        private String name ;

        //Mark
        private Float studentMark ;

        //Address
        private String city ;
        private String district ;
        private String street ;


    }
