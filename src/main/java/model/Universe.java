package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class Universe {
    String name;
    List<Dispute> disputes;

    public void checkDisputes() {
        disputes.forEach(val -> {
            if (val.check()) {
                val.members.forEach(memb -> memb.race.physicalManifestation = true);
            }
        });
    }

    public void addDispute(Dispute dispute) {
        if (this.disputes == null) {
            this.disputes = new ArrayList<>();
        }

        this.disputes.add(dispute);
    }
}
