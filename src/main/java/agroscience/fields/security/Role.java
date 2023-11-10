package agroscience.fields.security;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum Role{
    organization("organization"),
    worker("worker"),
    admin("admin");

    private final String vale;

    public static class Builder{
        List<Role> roles;

        public Builder() {
            this.roles = new ArrayList<>();
        }
        public Builder organization(){
            roles.add(organization);
            return this;
        }

        public Builder worker(){
            roles.add(worker);
            return this;
        }

        public Builder admin() {
            roles.add(admin);
            return this;
        }

        public List<Role> build(){
            return roles;
        }
    }
}
